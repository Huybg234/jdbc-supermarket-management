package repository;

import constant.DatabaseConstant;
import entity.Product;
import entity.Cashier;
import stafftimesheet.SellingTimeSheet;
import stafftimesheet.Selling;
import util.CollectionUtil;
import util.DatabaseConnection;
import util.ObjectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SellingDAO {
    private static final String STAFF_SELLING_TABLE_NAME = "selling";

    private static final String STAFF_ID = "cashier_id";
    private static final String ITEM_ID = "product_id";
    private static final String SOLD_ITEM_NUMBER = "sold_product_quantity";

    public static final Connection connection;

    static {
        connection = DatabaseConnection.openConnection(DatabaseConstant.DRIVER_STRING, DatabaseConstant.URL, DatabaseConstant.USERNAME, DatabaseConstant.PASSWORD);
    }

    public List<Selling> getSellingTimeSheet(){
        List<Selling> sellings = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "select s.id cashier_id, s.name cashier_name, s.address, s.phone_number, s.registration_date, it.id product_id, it.name product_name, it.import_cost, it.cost, it.category, ss.sold_product_quantity " +
                    "from " + STAFF_SELLING_TABLE_NAME + " ss join " + CashierDAO.STAFF_TABLE_NAME + " s on ss.cashier_id = s.id join " + ProductDAO.ITEM_TABLE_NAME + " it on ss.product_id = it.id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            sellings = new ArrayList<>();
            while (resultSet.next()){
                int staffID = resultSet.getInt(STAFF_ID);
                String staffName = resultSet.getString("cashier_name");
                String address = resultSet.getString(CashierDAO.ADDRESS);
                String phoneNumber = resultSet.getString(CashierDAO.PHONE_NUMBER);
                String day = resultSet.getString(CashierDAO.DAY);
                Cashier cashier = new Cashier(staffID, staffName, address,phoneNumber,day);

                int itemID = resultSet.getInt(ITEM_ID);
                String itemName = resultSet.getString("product_name");
                float buyPrice = resultSet.getFloat(ProductDAO.BUY_PRICE);
                float sellPrice = resultSet.getFloat(ProductDAO.SELL_PRICE);
                String itemGroup = resultSet.getString(ProductDAO.ITEM_GROUP);
                Product product = new Product(itemID, itemName, buyPrice, sellPrice, itemGroup);

                int soldItemNumber =resultSet.getInt(SOLD_ITEM_NUMBER);

                SellingTimeSheet sellingTimeSheet = new SellingTimeSheet(product,soldItemNumber);
                Selling tempSelling = searchStaff(sellings, staffID);

                if (ObjectUtil.isEmpty(tempSelling)) {
                    Selling selling = new Selling(cashier, Arrays.asList(sellingTimeSheet));
                    sellings.add(selling);
                } else {
                    int index = sellings.indexOf(tempSelling);
                    sellings.get(index).getSellingTimeSheets().add(sellingTimeSheet);
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(resultSet, preparedStatement, null);
        }
        return sellings;
    }

    private Selling searchStaff(List<Selling> sellings, int staffId) {

        List<Selling> collect = sellings.stream().filter(selling -> selling.getStaff().getId() == staffId).collect(Collectors.toList());
        if (!CollectionUtil.isEmpty(collect)) {
            collect.get(0);
        }
        return null;
    }

    public void insertNewStaffSelling(Selling selling) {
        if (ObjectUtil.isEmpty(selling)) {
            return;
        }
        int staffID = selling.getStaff().getId();
        selling.getSellingTimeSheets().forEach(timesheet -> {
            PreparedStatement preparedStatement = null;
            try {
                String query = "INSERT INTO " + STAFF_SELLING_TABLE_NAME + " VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, staffID);
                preparedStatement.setInt(2, timesheet.getItem().getId());
                preparedStatement.setInt(3, timesheet.getSoldProductQuantity());
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                DatabaseConnection.closeConnection(null, preparedStatement, null);
            }
        });
    }

    public void insertNewStaffSelling(List<Selling> sellings) {
        if (CollectionUtil.isEmpty(sellings)) {
            return;
        }
        sellings.forEach(this::insertNewStaffSelling);
    }
}
