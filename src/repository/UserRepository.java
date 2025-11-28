package repository;

import model.User;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public void insert(User user) {
        String sql = "INSERT INTO users (username, password, name, address, contact) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getContact());
            stmt.executeUpdate();

            // Lấy id tự sinh từ DB
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setUserId(rs.getString(1)); // nếu user_id kiểu int thì dùng getInt
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("contact"),
                        rs.getBoolean("svip"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public User findByUserId(String userId) {
        String sql = "SELECT * FROM users WHERE user_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("contact"),
                        rs.getBoolean("svip"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
