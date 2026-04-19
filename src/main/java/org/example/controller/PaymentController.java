package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.App;
import java.io.IOException;

public class PaymentController {
    @FXML private TextField cardNumberField;
    @FXML private TextField expiryField;
    @FXML private PasswordField cvvField;
    @FXML private Label paymentStatus;

    @FXML
    private void handleProcessPayment() {
        String card = cardNumberField.getText().replaceAll("\\s+", "");
        String expiry = expiryField.getText().trim();
        String cvv = cvvField.getText().trim();

        if (card.isEmpty() || expiry.isEmpty() || cvv.isEmpty()) {
            paymentStatus.setText("Заполните все поля!");
            return;
        }
        if (!card.matches("\\d{16}")) {
            paymentStatus.setText("Номер карты должен содержать 16 цифр!");
            return;
        }
        if (!expiry.matches("\\d{2}/\\d{2}")) {
            paymentStatus.setText("Срок действия в формате ММ/ГГ!");
            return;
        }
        if (!cvv.matches("\\d{3}")) {
            paymentStatus.setText("CVC должен содержать 3 цифры!");
            return;
        }

        paymentStatus.setText("");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успех");
        alert.setContentText("Оплата прошла успешно! Заказ создан.");
        alert.showAndWait();

        goBack();
    }

    @FXML
    private void handleCancel() {
        goBack();
    }

    private void goBack() {
        try {
            App.setRoot("main-view");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}