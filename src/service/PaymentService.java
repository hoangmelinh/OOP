package service;

import java.util.*;
import model.Payment;
import model.Ticket;
import repository.*;

public class PaymentService {
    private final TicketRepository ticketRepo;
    private final SeatRepository seatRepo;
    private final ShowtimeRepository showtimeRepo;
    private final UserRepository userRepo;
    private final FilmRepository filmRepo;
    private final CinemaRepository cinemaRepo;
    private final PaymentRepository paymentRepo;

    public PaymentService(TicketRepository ticketRepo, SeatRepository seatRepo, ShowtimeRepository showtimeRepo, UserRepository userRepo, FilmRepository filmRepo, CinemaRepository cinemaRepo, PaymentRepository paymentRepo) {
        this.ticketRepo = ticketRepo;
        this.seatRepo = seatRepo;
        this.showtimeRepo = showtimeRepo;
        this.userRepo = userRepo;
        this.cinemaRepo = cinemaRepo;
        this.filmRepo = filmRepo;
        this.paymentRepo = paymentRepo;
    }

    public void createPayment(Payment payment){
        paymentRepo.insert(payment);
    }

    public void updatePayment(Payment payment){
        paymentRepo.update(payment);
    }

    public List<Payment> getPaymentsByUserId(String userId){
        return paymentRepo.findByUserId(userId);
    }

    public Payment getPaymentById(String PaymentId){
        return paymentRepo.findById(PaymentId);
    }

}