package org.example.dao;

import org.example.util.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDAO {
    public void saveMessage(String sender, String text) {
        String query = "INSERT INTO chat_messages (sender, message, sent_at) VALUES (?, ?, NOW())";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, sender);
            pstmt.setString(2, text);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllMessages() {
        List<String> messages = new ArrayList<>();
        String query = "SELECT sender, message, sent_at FROM chat_messages ORDER BY sent_at ASC";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                messages.add(rs.getTimestamp("sent_at") + " | " +
                        rs.getString("sender") + ": " +
                        rs.getString("message"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }
}