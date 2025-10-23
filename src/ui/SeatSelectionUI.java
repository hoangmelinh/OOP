package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SeatSelectionUI extends JFrame {
    private static final int ROWS = 5;
    private static final int COLS = 5;
    private JButton[][] seats = new JButton[ROWS][COLS];
    private String selectedSeat = null;
    private double price = 120000.0;

    public SeatSelectionUI() {
        setTitle("Đặt vé xem phim");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(ROWS, COLS, 5, 5));

        // Tao ghe
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                String seatId = "A" + (i * COLS + j + 1);   // vi du A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A13
                seats[i][j] = new JButton(seatId);
                
                // Mot so ghe da duoc dat (mau do)
                if (isSeatBooked(seatId)) {
                    seats[i][j].setBackground(Color.RED);
                    seats[i][j].setEnabled(false);
                } else {
                    seats[i][j].setBackground(Color.GREEN);
                }
                
                add(seats[i][j]);
                seats[i][j].addActionListener(new SeatHandler(seatId, seats[i][j]));
            }
        }
    }

    private boolean isSeatBooked(String seatId) {
        // Mo phong mot so ghe da duoc dat
        String[] bookedSeats = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10", "A13"};
        for (String booked : bookedSeats) {
            if (booked.equals(seatId)) {
                return true;
            }
        }
        return false;
    }

    private class SeatHandler implements ActionListener {
        private String seatId;
        private JButton button;

        public SeatHandler(String seatId, JButton button) {
            this.seatId = seatId;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.getBackground() == Color.RED) {
                JOptionPane.showMessageDialog(null,
                        "Ghế " + seatId + " đã được đặt!");
            } else {
                selectedSeat = seatId;
                button.setBackground(Color.RED);
                showPaymentDialog();
            }
        }
    }

    private void showPaymentDialog() {
        PaymentUI paymentUI = new PaymentUI(selectedSeat, price);
        paymentUI.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SeatSelectionUI().setVisible(true);
        });
    }
}
