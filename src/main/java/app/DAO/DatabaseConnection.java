package app.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/cinema_ticket_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123456"; // đổi theo mật khẩu MySQL của bạn

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // load driver MySQL
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL Driver not found!", e);
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
