package ui;

import model.User;
import repo.InMemoryDb;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginUI extends JFrame {
    private InMemoryDb db;
    private JTextField loginEmailField, registerNameField, registerEmailField;
    private JPasswordField loginPasswordField, registerPasswordField;
    private JButton loginBtn, registerBtn, switchToRegisterBtn, switchToLoginBtn, forgotPasswordBtn;
    private JPanel loginPanel, registerPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // Colors for modern UI
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219);
    private static final Color SECONDARY_COLOR = new Color(46, 204, 113);
    private static final Color LIGHT_GRAY = new Color(236, 240, 241);
    private static final Color DARK_GRAY = new Color(44, 62, 80);
    
    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    public LoginUI() {
        db = new InMemoryDb();
        initComponents();
        setupLayout();
        addEventListeners();
        
        // Save users on window close as an extra safety
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                db.saveUsersToFile();
            }
        });
    }

    private void initComponents() {
        setTitle("🎬 Cinema Booking System - Đăng nhập");
        setSize(450, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        
        // CardLayout để chuyển đổi giữa login và register
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Color.WHITE);

        // Panel đăng nhập
        loginPanel = createLoginPanel();
        
        // Panel đăng ký
        registerPanel = createRegisterPanel();

        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(registerPanel, "REGISTER");
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);

        // Logo/Icon area
        JLabel logoLabel = new JLabel("🎬");
        logoLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(logoLabel, gbc);

        // Tiêu đề
        JLabel titleLabel = new JLabel("Cinema Booking");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(PRIMARY_COLOR);
        gbc.gridy = 1;
        panel.add(titleLabel, gbc);
        
        JLabel subtitleLabel = new JLabel("Đăng nhập vào tài khoản của bạn");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(DARK_GRAY);
        gbc.gridy = 2;
        panel.add(subtitleLabel, gbc);

        // Email
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        emailLabel.setForeground(DARK_GRAY);
        panel.add(emailLabel, gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        loginEmailField = new JTextField(20);
        loginEmailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginEmailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        loginEmailField.setPreferredSize(new Dimension(250, 40));
        panel.add(loginEmailField, gbc);

        // Mật khẩu
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        passwordLabel.setForeground(DARK_GRAY);
        panel.add(passwordLabel, gbc);
        
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        loginPasswordField = new JPasswordField(20);
        loginPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        loginPasswordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        loginPasswordField.setPreferredSize(new Dimension(250, 40));
        panel.add(loginPasswordField, gbc);

        // Forgot password button
        gbc.gridx = 1; gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 0;
        forgotPasswordBtn = new JButton("Quên mật khẩu?");
        forgotPasswordBtn.setBorderPainted(false);
        forgotPasswordBtn.setContentAreaFilled(false);
        forgotPasswordBtn.setForeground(PRIMARY_COLOR);
        forgotPasswordBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel.add(forgotPasswordBtn, gbc);

        // Nút đăng nhập
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        loginBtn = createStyledButton("Đăng nhập", SECONDARY_COLOR, new Dimension(250, 45));
        panel.add(loginBtn, gbc);

        // Divider
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(200, 1));
        panel.add(separator, gbc);

        // Nút chuyển sang đăng ký
        gbc.gridy = 8;
        switchToRegisterBtn = new JButton("Chưa có tài khoản? Đăng ký ngay");
        switchToRegisterBtn.setBorderPainted(false);
        switchToRegisterBtn.setContentAreaFilled(false);
        switchToRegisterBtn.setForeground(PRIMARY_COLOR);
        switchToRegisterBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(switchToRegisterBtn, gbc);

        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(30, 40, 30, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);

        // Logo/Icon area
        JLabel logoLabel = new JLabel("🎬");
        logoLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(logoLabel, gbc);

        // Tiêu đề
        JLabel titleLabel = new JLabel("Tạo tài khoản");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(PRIMARY_COLOR);
        gbc.gridy = 1;
        panel.add(titleLabel, gbc);
        
        JLabel subtitleLabel = new JLabel("Đăng ký để sử dụng dịch vụ đặt vé");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(DARK_GRAY);
        gbc.gridy = 2;
        panel.add(subtitleLabel, gbc);

        // Họ tên
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel nameLabel = new JLabel("Họ tên:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        nameLabel.setForeground(DARK_GRAY);
        panel.add(nameLabel, gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        registerNameField = new JTextField(20);
        registerNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        registerNameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        registerNameField.setPreferredSize(new Dimension(250, 40));
        panel.add(registerNameField, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        emailLabel.setForeground(DARK_GRAY);
        panel.add(emailLabel, gbc);
        
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        registerEmailField = new JTextField(20);
        registerEmailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        registerEmailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        registerEmailField.setPreferredSize(new Dimension(250, 40));
        panel.add(registerEmailField, gbc);

        // Mật khẩu
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        passwordLabel.setForeground(DARK_GRAY);
        panel.add(passwordLabel, gbc);
        
        gbc.gridx = 1; gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        registerPasswordField = new JPasswordField(20);
        registerPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        registerPasswordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        registerPasswordField.setPreferredSize(new Dimension(250, 40));
        panel.add(registerPasswordField, gbc);

        // Nút đăng ký
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        registerBtn = createStyledButton("Đăng ký", SECONDARY_COLOR, new Dimension(250, 45));
        panel.add(registerBtn, gbc);

        // Divider
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(200, 1));
        panel.add(separator, gbc);

        // Nút chuyển sang đăng nhập
        gbc.gridy = 8;
        switchToLoginBtn = new JButton("Đã có tài khoản? Đăng nhập ngay");
        switchToLoginBtn.setBorderPainted(false);
        switchToLoginBtn.setContentAreaFilled(false);
        switchToLoginBtn.setForeground(PRIMARY_COLOR);
        switchToLoginBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(switchToLoginBtn, gbc);

        return panel;
    }

    private void setupLayout() {
        add(mainPanel);
    }

    private JButton createStyledButton(String text, Color backgroundColor, Dimension size) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(size);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        
        // Add hover effects
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(backgroundColor.darker());
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        return button;
    }

    private void addEventListeners() {
        loginBtn.addActionListener(e -> handleLogin());
        registerBtn.addActionListener(e -> handleRegister());
        switchToRegisterBtn.addActionListener(e -> cardLayout.show(mainPanel, "REGISTER"));
        switchToLoginBtn.addActionListener(e -> cardLayout.show(mainPanel, "LOGIN"));
        forgotPasswordBtn.addActionListener(e -> handleForgotPassword());
        
        // Add Enter key listeners for better UX
        loginEmailField.addActionListener(e -> handleLogin());
        loginPasswordField.addActionListener(e -> handleLogin());
        registerNameField.addActionListener(e -> handleRegister());
        registerEmailField.addActionListener(e -> handleRegister());
        registerPasswordField.addActionListener(e -> handleRegister());
    }

    private void handleLogin() {
        String email = normalizeEmail(loginEmailField.getText());
        String password = new String(loginPasswordField.getPassword()).trim();

        // Validation
        if (email.isEmpty() || password.isEmpty()) {
            showErrorMessage("Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        
        if (!isValidEmail(email)) {
            showErrorMessage("Email không hợp lệ!");
            return;
        }
        
        if (password.length() < 6) {
            showErrorMessage("Mật khẩu phải có ít nhất 6 ký tự!");
            return;
        }
        
        // Tìm user trong database
        User user = findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            showSuccessMessage("Đăng nhập thành công!\nChào mừng " + user.getName() + "!");
            openSeatSelection();
        } else {
            showErrorMessage("Email hoặc mật khẩu không đúng!");
            // Clear password field for security
            loginPasswordField.setText("");
        }
    }
    
    private void handleRegister() {
        String name = registerNameField.getText().trim();
        String email = registerEmailField.getText().trim();
        String password = new String(registerPasswordField.getPassword());

        // Validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showErrorMessage("Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        
        if (name.length() < 2) {
            showErrorMessage("Họ tên phải có ít nhất 2 ký tự!");
            return;
        }
        
        if (!isValidEmail(email)) {
            showErrorMessage("Email không hợp lệ!");
            return;
        }
        
        if (password.length() < 6) {
            showErrorMessage("Mật khẩu phải có ít nhất 6 ký tự!");
            return;
        }

        if (findUserByEmail(email) != null) {
            showErrorMessage("Email đã tồn tại!");
            return;
        }

        // Tạo user mới
        String userId = "u" + (db.users.size() + 1);
        User newUser = new User(userId, name, email, password);
        db.users.put(userId, newUser);
        db.saveUsersToFile();

        showSuccessMessage("Đăng ký thành công!\nBạn có thể đăng nhập ngay bây giờ.");
        cardLayout.show(mainPanel, "LOGIN");
        
        // Clear register fields
        registerNameField.setText("");
        registerEmailField.setText("");
        registerPasswordField.setText("");
        
        // Pre-fill login form
        loginEmailField.setText(email);
    }

    private User findUserByEmail(String email) {
        String normalized = normalizeEmail(email);
        for (User user : db.users.values()) {
            if (normalizeEmail(user.getEmail()).equalsIgnoreCase(normalized)) {
                return user;
            }
        }
        return null;
    }

    private String normalizeEmail(String raw) {
        if (raw == null) return "";
        return raw.replace(" ", "").trim();
    }
    
    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
    
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void handleForgotPassword() {
        String email = JOptionPane.showInputDialog(this, 
            "Nhập email của bạn để khôi phục mật khẩu:", 
            "Quên mật khẩu", 
            JOptionPane.QUESTION_MESSAGE);
            
        if (email != null && !email.trim().isEmpty()) {
            User user = findUserByEmail(email.trim());
            if (user != null) {
                showSuccessMessage("Mật khẩu của bạn là: " + user.getPassword());
            } else {
                showErrorMessage("Email không tồn tại trong hệ thống!");
            }
        }
    }

    private void openSeatSelection() {
        this.dispose();
        new SeatSelectionUI().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginUI().setVisible(true);
        });
    }
}
