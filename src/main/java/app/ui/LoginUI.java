package app.ui;

import app.DAO.UserDAO;
import app.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class LoginUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private boolean isRegisterMode = false;
    private UserDAO userDAO;

    public LoginUI() {
        userDAO = new UserDAO();
        initUI();
    }

    private void initUI() {
        setTitle("Cinema Ticket System - Login");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Đăng nhập", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Mật khẩu:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        JLabel nameLabel = new JLabel("Tên:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(nameLabel, gbc);

        nameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(nameField, gbc);
        nameLabel.setVisible(false);
        nameField.setVisible(false);

        JButton actionButton = new JButton("Đăng nhập");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(actionButton, gbc);

        JButton toggleButton = new JButton("Chưa có tài khoản? Đăng ký");
        gbc.gridy = 5;
        panel.add(toggleButton, gbc);

        actionButton.addActionListener(e -> {
            if (isRegisterMode) handleRegister();
            else handleLogin();
        });

        toggleButton.addActionListener(e -> toggleMode(toggleButton, actionButton, titleLabel, nameLabel, nameField));

        add(panel);
    }

    private void toggleMode(JButton toggleButton, JButton actionButton, JLabel titleLabel, JLabel nameLabel, JTextField nameField) {
        isRegisterMode = !isRegisterMode;
        titleLabel.setText(isRegisterMode ? "Đăng ký tài khoản" : "Đăng nhập");
        actionButton.setText(isRegisterMode ? "Đăng ký" : "Đăng nhập");
        toggleButton.setText(isRegisterMode ? "Đã có tài khoản? Đăng nhập" : "Chưa có tài khoản? Đăng ký");
        nameLabel.setVisible(isRegisterMode);
        nameField.setVisible(isRegisterMode);
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        try {
            User user = userDAO.authenticateUser(email, password);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
                openSeatSelection();
            } else {
                JOptionPane.showMessageDialog(this, "Email hoặc mật khẩu không đúng.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    private void handleRegister() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        try {
            if (userDAO.emailExists(email)) {
                JOptionPane.showMessageDialog(this, "Email đã được sử dụng!");
                return;
            }

            int count = userDAO.getUserCount() + 1;
            User newUser = new User("U" + count, name, email, password);
            if (userDAO.createUser(newUser)) {
                JOptionPane.showMessageDialog(this, "Đăng ký thành công!");
                isRegisterMode = false;
            } else {
                JOptionPane.showMessageDialog(this, "Đăng ký thất bại!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    private void openSeatSelection() {
        JOptionPane.showMessageDialog(this, "Mở giao diện chọn ghế (chưa làm)");
        // new SeatSelectionUI().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
    }
}
