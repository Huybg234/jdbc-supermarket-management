package repository;

import constant.DatabaseConstant;
import entity.Item;
import util.CollectionUtil;
import util.DatabaseConnection;
import util.ObjectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public static final String ITEM_TABLE_NAME = "item";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String BUY_PRICE = "buyPrice";
    public static final String SELL_PRICE = "sellPrice";
    public static final String ITEM_GROUP = "itemGroup";

    private static final Connection connection;

    static {
        connection = DatabaseConnection.openConnection(DatabaseConstant.DRIVER_STRING, DatabaseConstant.URL, DatabaseConstant.USERNAME, DatabaseConstant.PASSWORD);
    }

    public List<Item> getItems() {
        List<Item> items = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM " + ITEM_TABLE_NAME;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            items = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                float buyPrice = resultSet.getFloat(BUY_PRICE);
                float sellPrice = resultSet.getFloat(SELL_PRICE);
                String itemGroup = resultSet.getString(ITEM_GROUP);
                Item item = new Item(id, name, buyPrice, sellPrice, itemGroup);
                if (ObjectUtil.isEmpty(item)) {
                    continue;
                }
                items.add(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(resultSet, preparedStatement, null);
        }
        return items;
    }
    public void insertNewItem(Item item){
        if (ObjectUtil.isEmpty(item)) {
            return;
        }
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO " + ITEM_TABLE_NAME + " VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,item.getId());
            preparedStatement.setString(2,item.getName());
            preparedStatement.setFloat(3,item.getBuyPrice());
            preparedStatement.setFloat(4,item.getSellPrice());
            preparedStatement.setString(5,item.getItemGroup());
            preparedStatement.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(null, preparedStatement, null);
        }
    }

    public void insertNewItem(List<Item> items) {
        if (!CollectionUtil.isEmpty(items)) {
            return;
        }
        items.forEach(this::insertNewItem);
    }
}
