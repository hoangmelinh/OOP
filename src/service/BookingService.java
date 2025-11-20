package service;

import model.*;
import model.Payment;
import model.Seat;
import model.Ticket;
import repository.*;
import repository.ShowtimeRepository;
import repository.TicketRepository;
import repository.UserRepository;


import java.util.Date;
import java.util.List;

public class BookingService {
    private final TicketRepository ticketRepo;
    private final SeatRepository seatRepo;
    private final ShowtimeRepository showtimeRepo;
    private final UserRepository userRepo;
    private final FilmRepository filmRepo;
    private final CinemaRepository cinemaRepo;
    private final PaymentRepository paymentRepo;

    public BookingService(TicketRepository ticketRepo, SeatRepository seatRepo, ShowtimeRepository showtimeRepo, UserRepository userRepo, FilmRepository filmRepo, CinemaRepository cinemaRepo, PaymentRepository paymentRepo) {
        this.ticketRepo = ticketRepo;
        this.seatRepo = seatRepo;
        this.showtimeRepo = showtimeRepo;
        this.userRepo = userRepo;
        this.cinemaRepo = cinemaRepo;
        this.filmRepo = filmRepo;
        this.paymentRepo = paymentRepo;
    }

    // Đặt vé
    public boolean bookTicket(Ticket ticket) {
        String getSeatId = ticket.getSeatId();
        String getShowtimeId = ticket.getShowtimeId();
        Seat seat = seatRepo.findById(getSeatId);
        if (seat.getShowtimeId().equals(getShowtimeId) && !seat.isStatus()) {
            ticketRepo.insert(ticket);
            // cap nhat ghe
            Seat Suseat = seatRepo.findById(ticket.getSeatId());
            Suseat.setStatus(true);
            seatRepo.update(Suseat);
            return true;
        }
        return false;
    }

    // Hủy vé
    public boolean cancelTicket(String ticketId) {
        Ticket ticket = ticketRepo.findById(ticketId);
        if (ticket != null && ticket.isStatus()) {
            ticket.setStatus(false);
            ticketRepo.update(ticket);
            return true;
        }
        return false;
    }

    // Xem lịch sử vé của user
    public List<Ticket> getUserTickets(String userId) {
        return ticketRepo.findByUserId(userId);
    }

    public String ShowTicketInfo(Ticket ticket){
        String username = userRepo.findByUserId(ticket.getUserId()).getUsername();
        String cinemaName = cinemaRepo.findById(showtimeRepo.findById(ticket.getShowtimeId()).getCinemaId()).getName();
        String filmName = filmRepo.findById(showtimeRepo.findById(ticket.getShowtimeId()).getFilmId()).getTitle();
        Date date = showtimeRepo.findById(ticket.getShowtimeId()).getDate();
        String room = showtimeRepo.findById(ticket.getShowtimeId()).getRoom();
        String seat = seatRepo.findById(ticket.getSeatId()).getRow() + seatRepo.findById(ticket.getSeatId()).getNumber();
        String status = ticket.isStatus() ? "Success" : "Unsuccess";

        String info ="Cinema: " + cinemaName + "\n"
                + "Movie: " + filmName + "\n"
                + "Room: " + room + "\n"
                + "Date: " + date + "\n"
                + "Seat: " + seat + "\n"
                + "User: " + username + "\n"
                + "Status: " + status+ "\n";

        return info;
    }
    public boolean isSeatAvailable(String seatId, String showtimeId) {
        Seat seat = seatRepo.findById(seatId);
        if (seat == null) return false;

        // Kiểm tra nếu ghế thuộc suất chiếu khác thì coi như không hợp lệ
        if (!seat.getShowtimeId().equals(showtimeId)) return false;

        // Kiểm tra ghế đã có vé đặt chưa
        List<Ticket> tickets = ticketRepo.findByShowtimeId(showtimeId);
        for (Ticket t : tickets) {
            if (t.getSeatId().equals(seatId) && t.isStatus()) {
                return false; // Ghế đã có người đặt
            }
        }
        return true;
    }
    public List<Seat> getAvailableSeats(String showtimeId) {
        List<Seat> allSeats = seatRepo.findByShowtimeId(showtimeId);
        List<Ticket> tickets = ticketRepo.findByShowtimeId(showtimeId);

        allSeats.removeIf(seat ->
                tickets.stream().anyMatch(t -> t.getSeatId().equals(seat.getSeatId()) && t.isStatus())
        );

        return allSeats;
    }

    public List<String> getShowtimesByFilm(String filmId) {
        return showtimeRepo.findByFilmId(filmId)
                .stream()
                .map(s -> s.getShowtimeId() + " - " + s.getDate() + " - Room " + s.getRoom())
                .toList();
    }

    public List<Showtime> getShowtimesByCinemaAndFilm(String cinemaId, String filmId) {
        return showtimeRepo.findByCinemaIdAndFilmId(cinemaId,filmId);
    }

    public Ticket getTicketById(String ticketId) {
        return ticketRepo.findById(ticketId);
    }

    public FilmRepository getFilmRepo() {
        return filmRepo;
    }

    public CinemaRepository getCinemaRepo() {
        return cinemaRepo;
    }

    public ShowtimeRepository getShowtimeRepo() {
        return showtimeRepo;
    }

    public List<Ticket> getTicketsByUser(User user) {

        return ticketRepo.findByUserId(user.getUserId());
    }

}

