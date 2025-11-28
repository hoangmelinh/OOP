package ui;

import model.User;
import service.UserService;

import javax.swing.*;
import java.awt.*;

public class SignInUI extends JFrame {
    private final UserService userService;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnSignIn, btnSignUp;

    public SignInUI(UserService userService) {
        this.userService = userService;

        setTitle("Sign In");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        JLabel lblTitle = new JLabel("Sign In");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);

        txtUsername = new JTextField(15);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtUsername, gbc);


        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Password:"), gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtPassword, gbc);


        btnSignIn = new JButton("Sign In");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnSignIn, gbc);


        btnSignUp = new JButton("Sign Up");
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnSignUp, gbc);

        add(panel);


        btnSignIn.addActionListener(e -> doLogin());
        btnSignUp.addActionListener(e -> openSignUp());
    }

    private void doLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ username và password!");
            return;
        }

        User user = userService.login(username, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công! Xin chào " + user.getName());
            dispose();
            User userfinal = userService.getUserById(user.getUserId());
 //           System.out.println(userfinal.isVipCheck());
            SwingUtilities.invokeLater(() -> new MainMenu(userfinal).setVisible(true));
        } else {
            JOptionPane.showMessageDialog(this, "Sai username hoặc password!", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openSignUp() {
        SwingUtilities.invokeLater(() -> {
            new SignUpUI(userService).setVisible(true);
        });
    }
}
