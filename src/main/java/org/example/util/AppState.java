package org.example.util;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.example.model.User;

public class AppState {
    private static final ObjectProperty<User> currentUser = new SimpleObjectProperty<>(null);

    public static User getCurrentUser() { return currentUser.get(); }
    public static void setCurrentUser(User user) { currentUser.set(user); }
    public static ObjectProperty<User> currentUserProperty() { return currentUser; }
}
// Session state
