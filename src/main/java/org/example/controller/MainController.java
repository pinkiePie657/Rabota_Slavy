package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.dao.ChatDAO;
import org.example.dao.ProductDAO;
import org.example.model.Product;
import org.example.model.User;
import org.example.util.AppState;
import org.example.App;
import java.io.IOException;
import java.util.List;

public class MainController {
    // Каталог
    @FXML private ListView<Product> productList;
    @FXML private ImageView productImage;
    @FXML private Label productTitle;
    @FXML private Label productDescription;

    // Личный кабинет (новый дизайн)
    @FXML private ImageView avatarImage;
    @FXML private Label profileUsername;
    @FXML private ListView<String> ordersList;

    // Чат с поддержкой
    @FXML private TextArea chatArea;
    @FXML private TextField messageField;

    private final ProductDAO productDAO = new ProductDAO();
    private final ChatDAO chatDAO = new ChatDAO();

    @FXML
    public void initialize() {
        // === КАТАЛОГ ===
        productList.getItems().setAll(productDAO.getAllProducts());
        productList.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> updateDetails(newVal));

        // === ЛИЧНЫЙ КАБИНЕТ ===
        User user = AppState.getCurrentUser();
        if (user != null) {
            profileUsername.setText(user.getUsername());
        } else {
            profileUsername.setText("Гость");
        }
        avatarImage.setImage(new Image("https://picsum.photos/id/64/120/120"));

        ordersList.getItems().setAll(
                "Заказ №1001 — 15.04.2026 (Профиль Rehau)",
                "Заказ №1002 — 10.04.2026 (Стеклопакет)"
        );

        // === ЧАТ ===
        loadChatMessages();
    }

    private void updateDetails(Product product) {
        if (product == null) return;

        productTitle.setText(product.getName());
        productDescription.setText(product.getDescription() != null
                ? product.getDescription()
                : "Описание отсутствует.");

        // === РЕАЛЬНЫЕ ФОТО ПО ТЕМЕ ОКОН ===
        String imageUrl = switch (product.getName()) {
            case "Профиль Rehau Grazio 70мм" -> "https://debesto.com/wp-content/uploads/2023/05/rehau-synego-70-1.jpg";
            case "Стеклопакет 40мм" -> "https://modernize.com/wp-content/uploads/2023/argon-krypton-window-glass.jpg";
            case "Фурнитура Roto NX" -> "https://ftt.roto-frank.com/int-en/media/products/roto-nx/roto-nx-hinge.jpg";
            case "Армирование 1.5мм" -> "https://ftt.roto-frank.com/int-en/media/products/roto-nx/roto-nx-2.jpg"; // альтернативный ракурс
            default -> "https://picsum.photos/id/870/400/300";
        };

        productImage.setImage(new Image(imageUrl, true)); // true = background loading
    }

    @FXML
    private void handleBuy() {
        User user = AppState.getCurrentUser();
        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Авторизация обязательна");
            alert.setContentText("Для оформления заказа необходимо войти в аккаунт или зарегистрироваться.");
            alert.showAndWait();
            try {
                App.setRoot("auth");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        if (productList.getSelectionModel().getSelectedItem() != null) {
            try {
                App.setRoot("payment-view");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // === ЧАТ ===
    private void loadChatMessages() {
        chatArea.clear();
        List<String> messages = chatDAO.getAllMessages();
        for (String msg : messages) {
            chatArea.appendText(msg + "\n");
        }
    }

    @FXML
    private void sendMessage() {
        String text = messageField.getText().trim();
        if (text.isEmpty()) return;

        String sender = AppState.getCurrentUser() != null ?
                AppState.getCurrentUser().getUsername() : "Гость";

        chatDAO.saveMessage(sender, text);
        loadChatMessages();
        messageField.clear();
    }

    // Выход из аккаунта
    @FXML
    private void handleLogout() {
        AppState.setCurrentUser(null);
        try {
            App.setRoot("auth");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}