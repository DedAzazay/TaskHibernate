package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Steve", "Stevanov", (byte) 22);
        userService.saveUser("Bob", "Bobanov", (byte) 26);
        userService.saveUser("Klark", "Klarkanov", (byte) 11);
        userService.saveUser("Djack", "Djackanov", (byte) 73);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
