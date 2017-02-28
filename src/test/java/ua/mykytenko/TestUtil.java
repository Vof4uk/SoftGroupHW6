package ua.mykytenko;

import ua.mykytenko.model.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;

public class TestUtil {
    public static final String SQL_INIT_DB;
    public static final String SQL_POPULATE_DB;

    static {
        SQL_INIT_DB = readResource("/db/initDB.sql");
        SQL_POPULATE_DB = readResource("/db/populateDB.sql");
    }

    private static String readResource(String resourceName){
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(ClassLoader.class.getResourceAsStream(resourceName)));
            while (reader.ready()){
                sb.append(reader.readLine());
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static void createTables(String sqlDriver, String url, String username, String password) {
        try {
            Class.forName(sqlDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username, password);
            executeQuery(connection, SQL_INIT_DB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void populateTables(String sqlDriver, String url, String username, String password) {
        try {
            Class.forName(sqlDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username, password);
            executeQuery(connection, SQL_POPULATE_DB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeQuery(Connection connection, String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
