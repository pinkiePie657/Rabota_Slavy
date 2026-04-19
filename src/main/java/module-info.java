module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires jbcrypt;
    opens org.example.controller to javafx.fxml;
    opens org.example to javafx.fxml;
    opens org.example.model to javafx.base;
    exports org.example;
}

