package model;

import java.util.Date;

public class Showtime {
    private String showtimeId;
    private String filmId;
    private String cinemaId;
    private Date date;
    private String room;

    public Showtime() {}

    public Showtime(String showtimeId, String filmId, String cinemaId, Date date, String room) {
        this.showtimeId = showtimeId;
        this.filmId = filmId;
        this.cinemaId = cinemaId;
        this.date = date;
        this.room = room;
    }

    public String getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(String showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getFilmId() {
        return filmId;
    }


    public String getCinemaId() {
        return cinemaId;
    }



    public Date getDate() {
        return date;
    }


    public String getRoom() {
        return room;
    }


    @Override
    public String toString() {
        return date + " (" + room + ")";
    }
}
