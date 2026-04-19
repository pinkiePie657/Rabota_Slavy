package org.example.dao;
import org.example.util.DatabaseManager;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
public class OrderDAO {
    public void makeOrder(int userId, int productId) {
        String sql = "{call create_order(?, ?)}";
        try (Connection conn = DatabaseManager.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, userId);
            cstmt.setInt(2, productId);
            cstmt.execute();
            System.out.println("Заказ успешно создан через хранимую процедуру!");
        } catch (SQLException e) {
            System.err.println("Ошибка при вызове процедуры: " + e.getMessage());
        }
    }
}