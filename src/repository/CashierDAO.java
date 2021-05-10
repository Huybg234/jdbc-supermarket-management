package repository;

import constant.DatabaseConstant;
import entity.Cashier;
import util.CollectionUtil;
import util.DatabaseConnection;
import util.ObjectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CashierDAO {
    public static final String STAFF_TABLE_NAME = "cashier";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String DAY = "registration_date";

    private static final Connection connection;

    static {
        connection = DatabaseConnection.openConnection(DatabaseConstant.DRIVER_STRING, DatabaseConstant.URL, DatabaseConstant.USERNAME, DatabaseConstant.PASSWORD);
    }

    public List<Cashier> getStaffs(){
        List<Cashier> cashiers = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM " + STAFF_TABLE_NAME;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            cashiers = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String address = resultSet.getString(ADDRESS);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                String day = resultSet.getString(DAY);
                Cashier cashier = new Cashier(id, name, address,phoneNumber,day);
                if (ObjectUtil.isEmpty(cashier)) {
                    continue;
                }
                cashiers.add(cashier);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(resultSet, preparedStatement, null);
        }
        return cashiers;
    }

    public void insertNewStaff(Cashier cashier){
        if (ObjectUtil.isEmpty(cashier)) {
            return;
        }
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO " + STAFF_TABLE_NAME + " VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cashier.getId());
            preparedStatement.setString(2, cashier.getName());
            preparedStatement.setString(3, cashier.getAddress());
            preparedStatement.setString(4, cashier.getPhoneNumber());
            preparedStatement.setString(5, cashier.getRegistrationDate());
            preparedStatement.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(null, preparedStatement, null);
        }
    }

    public void insertNewStaff(List<Cashier> cashiers) {
        if (CollectionUtil.isEmpty(cashiers)) {
            return;
        }
        cashiers.forEach(this::insertNewStaff);
    }
}
