package ru.akirakozov.sd.refactoring.database;

import ru.akirakozov.sd.refactoring.product.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DatabaseManager implements AutoCloseable {
    private final Connection connection;

    public DatabaseManager(String url) throws SQLException {
        connection = DriverManager.getConnection(url);
    }

    private void executeUpdate(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Product product) {
        executeUpdate("INSERT INTO PRODUCT " + "(NAME, PRICE) VALUES (\""
                + product.getName() + "\"," + product.getPrice() + ")");
    }

    private List<Product> executeQuery(String query, Action<ResultSet, List<Product>> action) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            return action.run(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getProducts() {
        return executeQuery("SELECT * FROM PRODUCT", resultSet -> {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(convertRowToProduct(resultSet));
            }

            return products;
        });
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    @FunctionalInterface
    private interface Action<A, B> {
        B run(A a) throws SQLException;
    }

    Product convertRowToProduct(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        int price = resultSet.getInt("price");
        return new Product(name, price);
    }
}
