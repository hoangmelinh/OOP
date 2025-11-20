import service.*;
import repository.*;
import ui.SignInUI;


public class Main {
    public static void main(String[] args) {
        // Tạo repository và service
        UserRepository userRepo = new UserRepository();  // giả sử có constructor mặc định
        UserService userService = new UserService(userRepo);

        // Mở giao diện đăng nhập
        javax.swing.SwingUtilities.invokeLater(() -> {
            new SignInUI(userService).setVisible(true);
        });
    }

}
