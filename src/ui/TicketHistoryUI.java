package ui;

import model.Payment;
import model.Ticket;
import model.User;
import service.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class TicketHistoryUI extends JFrame {
    private final User currentUser;
    private final PaymentService paymentService;
    private JTable paymentTable;
    private JTextArea ticketDetailArea;
    private final BookingService bookingService;

    public TicketHistoryUI(User currentUser) {
        this.currentUser = currentUser;
        this.paymentService = new PaymentService(
                new repository.TicketRepository(),
                new repository.SeatRepository(),
                new repository.ShowtimeRepository(),
                new repository.UserRepository(),
                new repository.FilmRepository(),
                new repository.CinemaRepository(),
                new repository.PaymentRepository()
        );
        this.bookingService = new BookingService(
                new repository.TicketRepository(),
                new repository.SeatRepository(),
                new repository.ShowtimeRepository(),
                new repository.UserRepository(),
                new repository.FilmRepository(),
                new repository.CinemaRepository(),
                new repository.PaymentRepository()
        );
        initComponents();
        loadPaymentHistory();
    }

    private void initComponents() {
        setTitle("Lịch sử thanh toán - " + currentUser.getUsername());
        setSize(850, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Lịch sử thanh toán của " + currentUser.getUsername(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));


        String[] columns = {"Mã thanh toán", "Mã vé", "Tổng tiền", "Trạng thái", "Hành động"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return column == 4;
            }
        };

        paymentTable = new JTable(model);
        paymentTable.setRowHeight(28);


        paymentTable.getColumn("Hành động").setCellRenderer(new ButtonRenderer());
        paymentTable.getColumn("Hành động").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane tableScroll = new JScrollPane(paymentTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Danh sách thanh toán"));


        ticketDetailArea = new JTextArea();
        ticketDetailArea.setEditable(false);
        ticketDetailArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane ticketScroll = new JScrollPane(ticketDetailArea);
        ticketScroll.setBorder(BorderFactory.createTitledBorder("Chi tiết vé"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tableScroll, ticketScroll);
        splitPane.setDividerLocation(500);

        JButton closeButton = new JButton("Đóng");
        closeButton.addActionListener(e -> dispose());
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeButton);

        add(titleLabel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        paymentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = paymentTable.getSelectedRow();
                if (row >= 0) {
                    String ticketId = paymentTable.getValueAt(row, 1).toString();
                    Ticket ticket = bookingService.getTicketById(ticketId);
                    if (ticket != null) {
                        ticketDetailArea.setText(bookingService.ShowTicketInfo(ticket));
                    } else {
                        ticketDetailArea.setText("Không tìm thấy thông tin vé!");
                    }
                }
            }
        });
    }

    private void loadPaymentHistory() {
        List<Payment> payments = paymentService.getPaymentsByUserId(currentUser.getUserId());
        DefaultTableModel model = (DefaultTableModel) paymentTable.getModel();
        model.setRowCount(0);

        if (payments == null || payments.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa có lịch sử thanh toán!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (Payment p : payments) {
            String action = p.isStatus() ? "" : "Thanh toán lại";
            model.addRow(new Object[]{
                    p.getPaymentId(),
                    p.getTicketId(),
                    p.getTotal(),
                    p.isStatus() ? "Thành công" : "Thất bại",
                    action
            });
        }
    }


    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (value == null || value.toString().isEmpty()) {
                setText("");
                setEnabled(false);
            } else {
                setText(value.toString());
                setEnabled(true);
            }
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.label = value != null ? value.toString() : "";
            this.row = row;
            button.setText(label);
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (clicked && label.equals("Thanh toán lại")) {
                String ticketId = paymentTable.getValueAt(row, 1).toString();
                Ticket ticket = bookingService.getTicketById(ticketId);

                String paymentId = paymentTable.getValueAt(row, 0).toString();
                Payment payment = paymentService.getPaymentById(paymentId);
                if (ticket != null) {
                    // Mở lại giao diện PaymentUI
                    new PaymentUI(paymentService, bookingService, ticket, payment).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy vé để thanh toán lại!");
                }
            }
            clicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
    }
}
