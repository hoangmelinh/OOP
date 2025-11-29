package ui;

import model.User;
import model.VipUser;
import service.BookingService;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private final User currentUser;

    public MainMenu(User user) {
        this.currentUser = user;
//        System.out.println(user.isVipCheck());
        setTitle("Main Menu");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());


        JLabel lblWelcome = new JLabel("Xin chào, " + currentUser.getName() + "!");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 18));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);


        JButton btnBooking = new JButton("Đặt vé");
        btnBooking.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton btnTicketHistory = new JButton("Lịch sử");
        btnBooking.setFont(new Font("Arial", Font.PLAIN, 16));


        btnBooking.addActionListener(e -> openBooking());

        btnTicketHistory.addActionListener(e -> openTicketHistory());


        JPanel centerPanel = new JPanel();
        centerPanel.add(btnBooking);
        centerPanel.add(btnTicketHistory);

        panel.add(lblWelcome, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        add(panel);
    }

    private void openBooking() {

        BookingService bookingService = new BookingService(
                new repository.TicketRepository(),
                new repository.SeatRepository(),
                new repository.ShowtimeRepository(),
                new repository.UserRepository(),
                new repository.FilmRepository(),
                new repository.CinemaRepository(),
                new repository.PaymentRepository()
        );

        SwingUtilities.invokeLater(() -> {
            new BookingUI(bookingService, currentUser).setVisible(true);
        });

        //dispose();
    }

    private void openTicketHistory(){
        SwingUtilities.invokeLater(() -> {
            new TicketHistoryUI(currentUser).setVisible(true);
        });
    }
}
