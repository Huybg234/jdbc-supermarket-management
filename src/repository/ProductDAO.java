package repository;

import constant.DatabaseConstant;
import entity.Product;
import util.CollectionUtil;
import util.DatabaseConnection;
import util.ObjectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static final String ITEM_TABLE_NAME = "product";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String BUY_PRICE = "import_cost";
    public static final String SELL_PRICE = "cost";
    public static final String ITEM_GROUP = "category";

    private static final Connection connection;

    static {
        connection = DatabaseConnection.openConnection(DatabaseConstant.DRIVER_STRING, DatabaseConstant.URL, DatabaseConstant.USERNAME, DatabaseConstant.PASSWORD);
    }

    public List<Product> getItems() {
        List<Product> products = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM " + ITEM_TABLE_NAME;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            products = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                float buyPrice = resultSet.getFloat(BUY_PRICE);
                float sellPrice = resultSet.getFloat(SELL_PRICE);
                String itemGroup = resultSet.getString(ITEM_GROUP);
                Product product = new Product(id, name, buyPrice, sellPrice, itemGroup);
                if (ObjectUtil.isEmpty(product)) {
                    continue;
                }
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(resultSet, preparedStatement, null);
        }
        return products;
    }
    public void insertNewItem(Product product){
        if (ObjectUtil.isEmpty(product)) {
            return;
        }
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO " + ITEM_TABLE_NAME + " VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getCategory());
            preparedStatement.setFloat(4, product.getImportCost());
            preparedStatement.setFloat(5, product.getCost());
            preparedStatement.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(null, preparedStatement, null);
        }
    }

    public void insertNewItem(List<Product> products) {
        if (CollectionUtil.isEmpty(products)) {
            return;
        }
        products.forEach(this::insertNewItem);
    }
}
