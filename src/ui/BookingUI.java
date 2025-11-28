package ui;

import model.*;
import repository.PaymentRepository;
import repository.SeatRepository;
import repository.UserRepository;
import service.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookingUI extends JFrame {
    private final BookingService bookingService;
    private final User user;
    private JComboBox<Cinema> cinemaCombo;
    private JComboBox<Film> filmCombo;
    private JComboBox<Showtime> showtimeCombo;
    private JComboBox<Seat> seatCombo;
    private JButton bookBtn;
    private Ticket ticket;
    private Payment payment;
    private UserService userService;

    public BookingUI(BookingService bookingService, User user) {
        this.userService = new UserService(new UserRepository());
        this.bookingService = bookingService;
        this.user = userService.getUserById(user.getUserId());

        setTitle("Booking Ticket UI");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Cinema:"));
        cinemaCombo = new JComboBox<>();
        loadCinemas();
        cinemaCombo.addActionListener(e -> loadFilmsByCinema());
        add(cinemaCombo);

        // Dropdown phim
        add(new JLabel("Film:"));
        filmCombo = new JComboBox<>();
        filmCombo.addActionListener(e -> loadShowtimes());
        add(filmCombo);

        // Dropdown suất chiếu
        add(new JLabel("Showtime:"));
        showtimeCombo = new JComboBox<>();
        showtimeCombo.addActionListener(e -> loadSeats());
        add(showtimeCombo);

        // Dropdown ghế
        add(new JLabel("Seat:"));
        seatCombo = new JComboBox<>();
        add(seatCombo);

        // Nút đặt vé
        bookBtn = new JButton("Book Ticket");
        bookBtn.addActionListener(e -> bookTicket());
        add(bookBtn);

    }

    private void loadCinemas() {
        List<Cinema> cinemas = bookingService.getCinemaRepo().findAll();
        cinemaCombo.removeAllItems();
        for (Cinema c : cinemas) {
            cinemaCombo.addItem(c);
        }
    }


    private void loadFilmsByCinema() {
        Cinema selected = (Cinema) cinemaCombo.getSelectedItem();
        if (selected == null) return;
        List<Film> films = bookingService.getShowtimeRepo().findFilmByCinemaId(selected.getCinemaId());
        filmCombo.removeAllItems();
        for (Film f : films) {
            filmCombo.addItem(f);
        }

        showtimeCombo.removeAllItems();
        seatCombo.removeAllItems();
    }

    private void loadShowtimes() {
        Cinema cinema = (Cinema) cinemaCombo.getSelectedItem();
        Film film = (Film) filmCombo.getSelectedItem();
        if (cinema == null || film == null) return;
        List<Showtime> showtimes = bookingService.getShowtimesByCinemaAndFilm(cinema.getCinemaId(), film.getFilmId());

        showtimeCombo.removeAllItems();
        for (Showtime s : showtimes) {
            showtimeCombo.addItem(s);
        }

        seatCombo.removeAllItems();
    }

    private void loadSeats() {
        Showtime selected = (Showtime) showtimeCombo.getSelectedItem();
        if (selected == null) return;
        List<Seat> availableSeats = bookingService.getAvailableSeats(selected.getShowtimeId());

        seatCombo.removeAllItems();
        for (Seat seat : availableSeats) {
            seatCombo.addItem(seat);
        }
    }

    private void bookTicket() {
        Showtime showtime = (Showtime) showtimeCombo.getSelectedItem();
        Seat seat = (Seat) seatCombo.getSelectedItem();

        if (showtime == null || seat == null) {
            JOptionPane.showMessageDialog(this, "Please select showtime and seat!");
            return;
        }

        ticket = new Ticket();
        ticket.setShowtimeId(showtime.getShowtimeId());
        ticket.setSeatId(seat.getSeatId());
        ticket.setUserId(user.getUserId());
        ticket.setStatus(true);


        if (bookingService.bookTicket(ticket)) {
            JOptionPane.showMessageDialog(this, "Booking successful!");
            // tao thanh toan
            SeatRepository seatRepo = new SeatRepository();
            Seat BookingSeat = seatRepo.findById(ticket.getSeatId());
            payment = new Payment();
            payment.setTicketId(ticket.getTicketId());
            payment.setUserId(ticket.getUserId());
            payment.setStatus(false);
            payment.setTotal(BookingSeat.getRow(), user.getBonus());



            openPaymentUI();
            dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Seat already booked!");
        }

    }

    private void openPaymentUI() {
        if (ticket == null) {
            JOptionPane.showMessageDialog(this, "Bạn chưa đặt vé để thanh toán!");
            return;
        }
        PaymentService paymentService = new PaymentService( new repository.TicketRepository(),
                new repository.SeatRepository(),
                new repository.ShowtimeRepository(),
                new repository.UserRepository(),
                new repository.FilmRepository(),
                new repository.CinemaRepository(),
                new repository.PaymentRepository());

        paymentService.createPayment(payment);
        SwingUtilities.invokeLater(() -> new PaymentUI(paymentService, bookingService, ticket, payment).setVisible(true));
    }

}
