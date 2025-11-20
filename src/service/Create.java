package service;

import java.text.SimpleDateFormat;
import java.util.*;

import repository.*;
import model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Create {
    private final TicketRepository ticketRepo;
    private final SeatRepository seatRepo;
    private final ShowtimeRepository showtimeRepo;
    private final UserRepository userRepo;
    private final FilmRepository filmRepo;
    private final CinemaRepository cinemaRepo;

    public Create(TicketRepository ticketRepo, SeatRepository seatRepo, ShowtimeRepository showtimeRepo, UserRepository userRepo, FilmRepository filmRepo, CinemaRepository cinemaRepo) {
        this.ticketRepo = ticketRepo;
        this.seatRepo = seatRepo;
        this.showtimeRepo = showtimeRepo;
        this.userRepo = userRepo;
        this.cinemaRepo = cinemaRepo;
        this.filmRepo = filmRepo;
    }

    public void CreateShowtimeAndSeat(Showtime showtime){
//        showtimeRepo.insert(showtime);
        for(int i = 1 ; i <= 5 ; i++) {
            for (int j = 1; j <= 5; j++) {
                Seat seat = new Seat();
                seat.setShowtimeId(showtime.getShowtimeId());
                seat.setRow(String.valueOf((char) ('A' + i - 1)));
                seat.setNumber(j);
                seat.setStatus(false);
                seatRepo.insert(seat);
            }
        }
    }

    public static void main(String[] args) {
        ShowtimeRepository showtimeRepo = new ShowtimeRepository();
        Create create = new Create( new repository.TicketRepository(),
                new repository.SeatRepository(),
                new repository.ShowtimeRepository(),
                new repository.UserRepository(),
                new repository.FilmRepository(),
                new repository.CinemaRepository());
        Showtime showtime = new Showtime();
//        Scanner sc = new Scanner(System.in);
//        showtime.setFilmId(sc.nextLine());
//        showtime.setCinemaId(sc.nextLine());
//        String dateStr = sc.nextLine();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       try {
//            Date date = formatter.parse(dateStr);
//            showtime.setDate(date);
//            showtime.setRoom(sc.nextLine());
//            create.CreateShowtimeAndSeat(showtime);
//            System.out.println("Tao thanh cong");
//        } catch (ParseException e) {
//            e.printStackTrace();

        List<Showtime> sts = showtimeRepo.findAll();
        for(Showtime st : sts){
            create.CreateShowtimeAndSeat(st);
            System.out.println(st.getShowtimeId());
        }



    }
}
