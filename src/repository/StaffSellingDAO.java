package repository;

import constant.DatabaseConstant;
import entity.Item;
import entity.Staff;
import stafftimesheet.SellingTimeSheet;
import stafftimesheet.StaffSelling;
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

public class StaffSellingDAO {
    private static final String STAFF_SELLING_TABLE_NAME = "selling";

    private static final String STAFF_ID = "staff_id";
    private static final String ITEM_ID = "item_id";
    private static final String SOLD_ITEM_NUMBER = "sold_item_number";

    public static final Connection connection;

    static {
        connection = DatabaseConnection.openConnection(DatabaseConstant.DRIVER_STRING, DatabaseConstant.URL, DatabaseConstant.USERNAME, DatabaseConstant.PASSWORD);
    }

    public List<StaffSelling> getStaffSellingTimeSheet(){
        List<StaffSelling> staffSellings = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "select s.id staff_id, s.name staff_name, s.address, s.phone_number, s.day, it.id item_id, it.name item_name, it.buyPrice, it.sellPrice, it.itemGroup, ss.soldItemNumber " +
                    "from " + STAFF_SELLING_TABLE_NAME + " ss join " + StaffDAO.STAFF_TABLE_NAME + " s on ss.staff_Id = s.id join " + ItemDAO.ITEM_TABLE_NAME + " it on ss.item_Id = it.id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            staffSellings = new ArrayList<>();
            while (resultSet.next()){
                int staffID = resultSet.getInt(STAFF_ID);
                String staffName = resultSet.getString(StaffDAO.NAME);
                String address = resultSet.getString(StaffDAO.ADDRESS);
                String phoneNumber = resultSet.getString(StaffDAO.PHONE_NUMBER);
                String day = resultSet.getString(StaffDAO.DAY);
                Staff staff = new Staff(staffID, staffName, address,phoneNumber,day);

                int itemID = resultSet.getInt(ITEM_ID);
                String itemName = resultSet.getString(ItemDAO.NAME);
                float buyPrice = resultSet.getFloat(ItemDAO.BUY_PRICE);
                float sellPrice = resultSet.getFloat(ItemDAO.SELL_PRICE);
                String itemGroup = resultSet.getString(ItemDAO.ITEM_GROUP);
                Item item = new Item(itemID, itemName, buyPrice, sellPrice, itemGroup);

                int soldItemNumber =resultSet.getInt(SOLD_ITEM_NUMBER);

                SellingTimeSheet sellingTimeSheet = new SellingTimeSheet(item,soldItemNumber);
                StaffSelling tempStaffSelling = searchStaff(staffSellings, staffID);

                if (ObjectUtil.isEmpty(tempStaffSelling)) {
                    StaffSelling staffSelling = new StaffSelling(staff, Arrays.asList(sellingTimeSheet));
                    staffSellings.add(staffSelling);
                } else {
                    int index = staffSellings.indexOf(tempStaffSelling);
                    staffSellings.get(index).getSellingTimeSheets().add(sellingTimeSheet);
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(resultSet, preparedStatement, null);
        }
        return staffSellings;
    }

    private StaffSelling searchStaff(List<StaffSelling> staffSellings, int staffId) {

        List<StaffSelling> collect = staffSellings.stream().filter(staffSelling -> staffSelling.getStaff().getId() == staffId).collect(Collectors.toList());
        if (!CollectionUtil.isEmpty(collect)) {
            collect.get(0);
        }
        return null;
    }

    public void insertNewStaffSelling(StaffSelling staffSelling) {
        if (ObjectUtil.isEmpty(staffSelling)) {
            return;
        }
        int staffID = staffSelling.getStaff().getId();
        staffSelling.getSellingTimeSheets().forEach(timesheet -> {
            PreparedStatement preparedStatement = null;
            try {
                String query = "INSERT INTO " + STAFF_SELLING_TABLE_NAME + " VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, staffID);
                preparedStatement.setInt(2, timesheet.getItem().getId());
                preparedStatement.setInt(3, timesheet.getSoldItemNumber());
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                DatabaseConnection.closeConnection(null, preparedStatement, null);
            }
        });
    }

    public void insertNewStaffSelling(List<StaffSelling> staffSellings) {
        if (!CollectionUtil.isEmpty(staffSellings)) {
            return;
        }
        staffSellings.forEach(this::insertNewStaffSelling);
    }
}
