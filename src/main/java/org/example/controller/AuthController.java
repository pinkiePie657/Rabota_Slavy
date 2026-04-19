package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.App;
import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.util.AppState;
import java.io.IOException;

public class AuthController {
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void handleLogin() {
        processLogin();
    }

    @FXML
    private void handleRegistration() {
        processRegistration();
    }

    @FXML
    private void handleGuest() {
        AppState.setCurrentUser(null);
        try {
            App.setRoot("main-view");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLogin() {
        String login = loginField.getText().trim();
        String pass = passwordField.getText();
        if (login.isEmpty() || pass.isEmpty()) {
            statusLabel.setText("Заполните все поля!");
            return;
        }
        User user = userDAO.login(login, pass);
        if (user != null) {
            AppState.setCurrentUser(user);
            try {
                App.setRoot("main-view");
            } catch (IOException e) {
                statusLabel.setText("Ошибка загрузки главного экрана");
            }
        } else {
            statusLabel.setText("Неверный логин или пароль!");
        }
    }

    private void processRegistration() {
        String login = loginField.getText().trim();
        String pass = passwordField.getText();
        if (login.isEmpty() || pass.isEmpty()) {
            statusLabel.setText("Заполните все поля!");
            return;
        }
        if (userDAO.register(login, pass)) {
            statusLabel.setText("Регистрация успешна! Выполняется вход...");
            User user = userDAO.login(login, pass);
            if (user != null) AppState.setCurrentUser(user);
            try {
                App.setRoot("main-view");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            statusLabel.setText("Пользователь уже существует!");
        }
    }
}