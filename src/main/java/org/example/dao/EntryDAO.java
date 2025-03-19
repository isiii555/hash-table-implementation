package org.example.dao;

import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntryDAO {
    public void saveEntry(int bucketId, int key, String value) {
        String sql = "INSERT INTO entries (bucket_id, [key], value) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bucketId);
            stmt.setInt(2, key);
            stmt.setString(3, value);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
