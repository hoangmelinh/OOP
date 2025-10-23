package repo;

import model.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDb {
    public final Map<String, User> users = new HashMap<>();
    public final Map<String, Movie> movies = new HashMap<>();
    public final Map<String, Cinema> cinemas = new HashMap<>();
    public final Map<String, ShowTime> showTimes = new HashMap<>();
    public final Map<String, Seat> seats = new HashMap<>();
    public final Map<String, Ticket> tickets = new HashMap<>();
    public final Map<String, Payment> payments = new HashMap<>();
    public InMemoryDb() {
        loadUsersFromFile();
        seedNonUserData();
        if (users.isEmpty()) {
            users.put("u1", new User("u1", "Nguyen Van A", "a@example.com", "123456"));
            saveUsersToFile();
        }
    }
    private void seedNonUserData() {
        // Cinemas
        cinemas.put("c1", new Cinema("c1", "CGV Landmark", "720A Dien Bien Phu"));
        cinemas.put("c2", new Cinema("c2", "BHD Bitexco", "2 Hai Trieu"));
        // Movies
        movies.put("m1", new Movie("m1", "Inception", "A mind-bending thriller", "Sci-Fi"));
        movies.put("m2", new Movie("m2", "Your Name", "A body-swapping romance", "Animation"));
        // ShowTimes
        showTimes.put("st1", new ShowTime("st1", "m1", "c1", LocalDate.now().plusDays(1), "Room 1", LocalTime.of(19, 30)));
        showTimes.put("st2", new ShowTime("st2", "m1", "c1", LocalDate.now().plusDays(1), "Room 1", LocalTime.of(21, 45)));
        showTimes.put("st3", new ShowTime("st3", "m2", "c2", LocalDate.now().plusDays(2), "Room 3", LocalTime.of(18, 0)));
        // Seats (simple 3x3 grid for demo)
        int seatCounter = 1;
        for (char r = 'A'; r <= 'C'; r++) {
            for (int n = 1; n <= 3; n++) {
                String id = "s" + seatCounter++;
                seats.put(id, new Seat(id, String.valueOf(r), n, "AVAILABLE"));
            }
        }
    }

    private File getUserStoreFile() {
        return new File("users.csv");
    }

    public void loadUsersFromFile() {
        File file = getUserStoreFile();
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split(",", -1);
                if (parts.length < 4) continue;
                String userId = parts[0].trim();
                String name = parts[1].trim();
                String email = parts[2].replace(" ", "").trim();
                String password = parts[3].trim();
                users.put(userId, new User(userId, name, email, password));
            }
        } catch (IOException e) {
            // ignore for now in demo
        }
    }

    public void saveUsersToFile() {
        File file = getUserStoreFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Header for clarity (commented so loader skips it)
            writer.write("# userId,name,email,password");
            writer.newLine();
            for (User u : users.values()) {
                String line = String.join(",",
                        safeCsv(u.getUserId()),
                        safeCsv(u.getName()),
                        safeCsv(u.getEmail()),
                        safeCsv(u.getPassword())
                );
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            // ignore for now in demo
        }
    }

    private String safeCsv(String value) {
        if (value == null) return "";
        return value.replace("\n", " ").replace(",", " ");
    }
}
