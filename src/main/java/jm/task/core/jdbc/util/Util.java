package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String HOST = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false";
    private final String USER = "rood";
    private final String PASSWORD = "rood";

    private Connection connection;
    private Statement statement;
//    PreparedStatement preparedStatement;

    public Util() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST, USER, PASSWORD);
            statement = connection.createStatement();
            if (!connection.isClosed()) {
//                System.out.println("Соединение установленно");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("driver not found");
        } catch (SQLException e) {
            System.out.println("SQL problem with connecting +\n");
            e.printStackTrace();
        }
    }

    public PreparedStatement getPreparedStatement(String sql) {
        try {
            return this.getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("SQL problem with preparedStatement");
            e.printStackTrace();
        }
        return null;
    }
    public Connection getConnection() {
        return connection;
    }
//    public Statement getStatement() {
//        return statement;
//    }

    public void connectionCloser() {
        try {
            connection.close();
//            System.out.println("Соединение успешно закрыто");
        } catch (SQLException e) {
            System.out.println("SQL problem with close");
            e.printStackTrace();
        }
    }
}
