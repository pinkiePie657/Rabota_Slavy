package org.example.dao;

import org.example.model.Product;
import org.example.util.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, price, description FROM products";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}

