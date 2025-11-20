// ShowtimeRepository.java
package repository;

import java.util.*;
import model.Film;
import model.Showtime;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ShowtimeRepository {
    public void insert(Showtime showtime) {
        String sql = "INSERT INTO showtimes (film_id, cinema_id, date, room) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, showtime.getFilmId());
            stmt.setString(2, showtime.getCinemaId());
            stmt.setTimestamp(3, new Timestamp(showtime.getDate().getTime()));
            stmt.setString(4, showtime.getRoom());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    showtime.setShowtimeId(rs.getString(1)); // nếu user_id kiểu int thì dùng getInt
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Showtime findById(String showtimeId) {
        String sql = "SELECT * FROM showtimes WHERE showtime_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, showtimeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Showtime(
                        rs.getString("showtime_id"),
                        rs.getString("film_id"),
                        rs.getString("cinema_id"),
                        rs.getTimestamp("date"),
                        rs.getString("room")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Showtime> findAll() {
        List<Showtime> list = new ArrayList<>();
        String sql = "SELECT * FROM showtimes";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Showtime(
                        rs.getString("showtime_id"),
                        rs.getString("film_id"),
                        rs.getString("cinema_id"),
                        rs.getTimestamp("date"),
                        rs.getString("room")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Showtime showtime) {
        String sql = "UPDATE showtimes SET film_id=?, cinema_id=?, date=?, room=? WHERE showtime_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, showtime.getFilmId());
            stmt.setString(2, showtime.getCinemaId());
            stmt.setTimestamp(3, new Timestamp(showtime.getDate().getTime()));
            stmt.setString(4, showtime.getRoom());
            stmt.setString(5, showtime.getShowtimeId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String showtimeId) {
        String sql = "DELETE FROM showtimes WHERE showtime_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, showtimeId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Showtime> findByFilmId(String filmId) {
        List<Showtime> list = new ArrayList<>();
        String sql = "SELECT * FROM showtimes WHERE film_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, filmId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Showtime(
                        rs.getString("showtime_id"),
                        rs.getString("film_id"),
                        rs.getString("cinema_id"),
                        rs.getDate("date"),
                        rs.getString("room")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Showtime> findByCinemaIdAndFilmId(String CinemaId, String FilmId) {
        List<Showtime> list = new ArrayList<>();
        String sql = "SELECT * FROM showtimes WHERE cinema_id = ? AND film_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, CinemaId);
            stmt.setString(2, FilmId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Showtime(
                        rs.getString("showtime_id"),
                        rs.getString("film_id"),
                        rs.getString("cinema_id"),
                        rs.getDate("date"),
                        rs.getString("room")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Film> findFilmByCinemaId(String CinemaId){
        FilmRepository filmRepo = new FilmRepository();
        Set<String> set = new HashSet<>();
        String sql = "SELECT film_id FROM showtimes WHERE cinema_id = ? ";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, CinemaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                set.add(rs.getString("film_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Film> list = new ArrayList<>();
        for(String i : set){
            list.add(filmRepo.findById(i));
        }
        return list;
    }

}
