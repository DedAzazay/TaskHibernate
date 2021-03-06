package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final String CREATE_TABLE = "CREATE TABLE `mydbtest`.`users` " +
            "(`id` BIGINT NOT NULL AUTO_INCREMENT," +
            " `name` VARCHAR(45) NOT NULL," +
            " `lastName` VARCHAR(45) NOT NULL," +
            " `age` TINYINT NOT NULL," +
            " PRIMARY KEY (`id`))";

    private final String DELETE_TABLE = "DROP TABLE IF EXISTS users";
    private final String SAVE_NEW_USER = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    private final String GET_ALL_USERS = "SELECT * FROM users";
    private final String CLEAR_TABLE = "DELETE FROM users";
    private final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";

    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Util bdWorker;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try {
            bdWorker = new Util();
            preparedStatement = bdWorker.getPreparedStatement(CREATE_TABLE);
            preparedStatement.executeUpdate();
//          System.out.println("Таблица успешна создана");
            bdWorker.connectionCloser();
        } catch (SQLException e) {
            System.out.println("Таблица уже существует");
        }
    }

    public void dropUsersTable() {
        try {
            bdWorker = new Util();
            preparedStatement = bdWorker.getPreparedStatement(DELETE_TABLE);
            preparedStatement.executeUpdate();
//            System.out.println("Таблица успешно удалена");
            bdWorker.connectionCloser();
        } catch (SQLException e) {
            System.out.println("SQL problem with delete table");
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            bdWorker = new Util();
            preparedStatement = bdWorker.getPreparedStatement(SAVE_NEW_USER);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " успешно добавлен в базу данных");
            bdWorker.connectionCloser();
        } catch (SQLException e) {
            System.out.println("SQL problem with save");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            bdWorker = new Util();
            preparedStatement = bdWorker.getPreparedStatement(DELETE_USER_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
//            System.out.println("Пользователь успешно удалён");
            bdWorker.connectionCloser();
        } catch (SQLException e) {
            System.out.println("SQL problem with delete table");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try {
            bdWorker = new Util();
            preparedStatement = bdWorker.getPreparedStatement(GET_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
//            System.out.println("Все пользователи получены");
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                result.add(user);
            }
            bdWorker.connectionCloser();
        } catch (SQLException e) {
            System.out.println("SQL problem with create");
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        try {
            bdWorker = new Util();
            preparedStatement = bdWorker.getPreparedStatement(CLEAR_TABLE);
            preparedStatement.executeUpdate();
//            System.out.println("Таблица успешно очищена");
            bdWorker.connectionCloser();
        } catch (SQLException e) {
            System.out.println("SQL problem with cleaning table");
            e.printStackTrace();
        }
    }
}
