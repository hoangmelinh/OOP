package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentUI extends JDialog {
    private String seatId;
    private double price;

    public PaymentUI(String seatId, double price) {
        this.seatId = seatId;
        this.price = price;
        initComponents();
    }

    private void initComponents() {
        setTitle("Thanh toán");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        // Panel thông tin
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel seatLabel = new JLabel("Ghế: " + seatId);
        seatLabel.setFont(new Font("Arial", Font.BOLD, 14));
        seatLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel priceLabel = new JLabel("Số tiền: " + String.format("%.0f", price));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel currencyLabel = new JLabel("VND");
        currencyLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        currencyLabel.setHorizontalAlignment(SwingConstants.CENTER);

        infoPanel.add(seatLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(currencyLabel);

        // Panel nút bấm
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton payButton = new JButton("Thanh toán");
        JButton cancelButton = new JButton("Hủy");

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPayment();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(payButton);
        buttonPanel.add(cancelButton);

        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void processPayment() {
        // Mô phỏng thanh toán
        JOptionPane.showMessageDialog(this, 
            "Thanh toán thành công!\n" +
            "Ghế: " + seatId + "\n" +
            "Số tiền: " + String.format("%.0f", price) + " VND",
            "Thông báo", 
            JOptionPane.INFORMATION_MESSAGE);
        
        dispose();
    }
}
