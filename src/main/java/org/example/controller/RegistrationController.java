package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.model.User;
import org.example.util.AppState;
import java.io.IOException;

public class RegistrationController {
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleRegister() {
        String login = loginField.getText();
        String pass = passwordField.getText();

        if (!login.isEmpty() && !pass.isEmpty()) {
            // Сохраняем тебя в системе
            User newUser = new User(login, pass);
            AppState.setCurrentUser(newUser);

            try {
                // ТЕПЕРЬ ПЕРЕКИДЫВАЕТ СЮДА
                App.setRoot("main-view");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}