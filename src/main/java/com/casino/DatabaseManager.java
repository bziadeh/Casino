package com.casino;

public class DatabaseManager {

    private static DatabaseManager manager;

    private DatabaseManager() {
        // private constructor, singleton design pattern
    }

    public static DatabaseManager get() {
        return (manager == null ? manager = new DatabaseManager() : manager);
    }

    public boolean addUser(String userId, String password) {
        // todo: implement addUser()
        return false;
    }

    public boolean deleteUser(String userId) {
        // todo: implement deleteUser()
        return false;
    }

    public boolean setBalance(String userId, double amount) {
        // todo: implement setBalance()
        return false;
    }
}
