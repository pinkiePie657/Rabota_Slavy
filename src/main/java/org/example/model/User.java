package org.example.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;

    // Для гостя / быстрой авторизации
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "CLIENT";
    }

    // Для DAO после проверки пароля
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
}