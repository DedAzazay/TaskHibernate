package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String HOST = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false";
    private final String USER = "rood";
    private final String PASSWORD = "rood";

    private Connection connection;

    public Util() {

    }

    private static SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        try {
            Configuration cfg = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mydbtest?useSSL=false");
            settings.put(Environment.USER, "rood");
            settings.put(Environment.PASS, "rood");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            settings.put(Environment.POOL_SIZE, 1);
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.USE_REFLECTION_OPTIMIZER,"false");
            settings.put(Environment.HBM2DDL_AUTO, "update");
            cfg.setProperties(settings);
            cfg.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties()).build();
            return cfg.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

    public void getJDBCUtil() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST, USER, PASSWORD);
            connection.createStatement();
            if (!connection.isClosed()) {

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
