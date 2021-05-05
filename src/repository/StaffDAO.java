package repository;

import constant.DatabaseConstant;
import entity.Staff;
import util.CollectionUtil;
import util.DatabaseConnection;
import util.ObjectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {
    public static final String STAFF_TABLE_NAME = "cashier";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String DAY = "day";

    private static final Connection connection;

    static {
        connection = DatabaseConnection.openConnection(DatabaseConstant.DRIVER_STRING, DatabaseConstant.URL, DatabaseConstant.USERNAME, DatabaseConstant.PASSWORD);
    }

    public List<Staff> getStaffs(){
        List<Staff> staffs = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM " + STAFF_TABLE_NAME;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            staffs = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                String address = resultSet.getString(ADDRESS);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                String day = resultSet.getString(DAY);
                Staff staff = new Staff(id, name, address,phoneNumber,day);
                if (ObjectUtil.isEmpty(staff)) {
                    continue;
                }
                staffs.add(staff);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(resultSet, preparedStatement, null);
        }
        return staffs;
    }

    public void insertNewStaff(Staff staff){
        if (ObjectUtil.isEmpty(staff)) {
            return;
        }
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO " + STAFF_TABLE_NAME + " VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, staff.getId());
            preparedStatement.setString(2,staff.getName());
            preparedStatement.setString(3,staff.getAddress());
            preparedStatement.setString(4,staff.getPhoneNumber());
            preparedStatement.setString(5,staff.getDay());
            preparedStatement.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(null, preparedStatement, null);
        }
    }

    public void insertNewStaff(List<Staff> staffs) {
        if (!CollectionUtil.isEmpty(staffs)) {
            return;
        }
        staffs.forEach(this::insertNewStaff);
    }
}
