package repository;

import model.Film;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository {

    public void insert(Film film) {
        String sql = "INSERT INTO films (title, genre, duration, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getGenre());
            stmt.setInt(3, film.getDuration());
            stmt.setString(4, film.getDescription());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    film.setFilmId(rs.getString(1)); // nếu user_id kiểu int thì dùng getInt
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Film findById(String id) {
        String sql = "SELECT * FROM films WHERE film_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Film(rs.getString("film_id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("duration"),
                        rs.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
