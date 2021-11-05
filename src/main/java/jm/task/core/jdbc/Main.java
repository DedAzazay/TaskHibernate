package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        int i = 0;
        userService.saveUser("Steve", "Stevanov", (byte) 22);
        System.out.println("User s imenen " + userService.getAllUsers().get(i++).getName() + " dobavlen v BD");
        userService.saveUser("Bob", "Bobanov", (byte) 26);
        System.out.println("User s imenen " + userService.getAllUsers().get(i++).getName() + " dobavlen v BD");
        userService.saveUser("Klark", "Klarkanov", (byte) 11);
        System.out.println("User s imenen " + userService.getAllUsers().get(i++).getName() + " dobavlen v BD");
        userService.saveUser("Djack", "Djackanov", (byte) 73);
        System.out.println("User s imenen " + userService.getAllUsers().get(i++).getName() + " dobavlen v BD");
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.shutdown();
    }
}
