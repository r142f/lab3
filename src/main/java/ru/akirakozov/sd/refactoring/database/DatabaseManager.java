package ru.akirakozov.sd.refactoring.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class DatabaseManager implements AutoCloseable {
    private final Connection connection;

    public DatabaseManager(String url) throws SQLException {
        connection = DriverManager.getConnection(url);
    }

    private void executeUpdate(String query) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(String name, long price) {
        executeUpdate("INSERT INTO PRODUCT " + "(NAME, PRICE) VALUES (\"" + name + "\"," + price + ")");
    }

    private ResultSet executeQuery(String query) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getProducts() {
        return executeQuery("SELECT * FROM PRODUCT");
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
