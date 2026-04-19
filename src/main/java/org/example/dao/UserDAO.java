package org.example.dao;

import org.example.model.User;
import org.example.util.DatabaseManager;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class UserDAO {
    public User login(String username, String password) {
        String sql = "SELECT id, username, password_hash, role FROM users WHERE username = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    if (BCrypt.checkpw(password, storedHash)) {
                        return new User(
                                rs.getInt("id"),
                                rs.getString("username"),
                                storedHash,           // пароль больше не нужен
                                rs.getString("role")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean register(String username, String password) {
        String sql = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, 'CLIENT')";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}