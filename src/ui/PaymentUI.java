package ui;

import model.Payment;
import model.Ticket;
import service.BookingService;
import service.PaymentService;

import javax.swing.*;
import java.awt.*;

public class PaymentUI extends JFrame {
    private final PaymentService paymentService;
    private final BookingService bookingService;
    private final Ticket ticket;
    private final Payment payment;

    private JLabel lblTicketInfo;
    private JLabel lblTotal;
    private JButton btnConfirm;
    private JButton btnCancel;

    public PaymentUI(PaymentService paymentService, BookingService bookingService, Ticket ticket, Payment payment) {
        this.paymentService = paymentService;
        this.bookingService = bookingService;
        this.ticket = ticket;
        this.payment = payment;

        setTitle("Payment Confirmation");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));


        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Ticket Information"));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);


        String ticketInfo = bookingService.ShowTicketInfo(ticket);

        JLabel lblTicketInfo = new JLabel();
        lblTicketInfo.setFont(new Font("Arial", Font.PLAIN, 14));

        // 🔤 Format HTML để hiển thị xuống dòng + căn trái
        String formattedInfo = ticketInfo
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\n", "<br>");

        lblTicketInfo.setText("<html><body style='width:300px; text-align:left; line-height:1.6;'>"
                + formattedInfo
                + "</body></html>");

        JLabel lblTotal = new JLabel("Total: " + payment.getTotal() + " VND");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        lblTotal.setForeground(new Color(0, 128, 0));
        lblTotal.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblTotal.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        infoPanel.add(lblTicketInfo);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(lblTotal);

        JScrollPane scrollPane = new JScrollPane(infoPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // 🎯 Panel chứa nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnConfirm = new JButton("Confirm Payment");
        JButton btnCancel = new JButton("Cancel");

        btnConfirm.addActionListener(e -> confirmPayment());
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnConfirm);
        buttonPanel.add(btnCancel);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    private void confirmPayment() {
        // trang thai thanh toan
        payment.setStatus(true);
        paymentService.updatePayment(payment);

        JOptionPane.showMessageDialog(this, "Thanh toán thành công!\nCảm ơn bạn đã sử dụng dịch vụ.");
        dispose();
    }
}
