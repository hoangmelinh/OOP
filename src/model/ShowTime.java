package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowTime {
    private String showTimeId;
    private String movieId;
    private LocalDate date;
    private String room;
    private LocalTime time;
    private String cinemaId;

    public ShowTime() {}

    public ShowTime(String showTimeId, String movieId, String cinemaId, LocalDate date, String room, LocalTime time) {
        this.showTimeId = showTimeId;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.date = date;
        this.room = room;
        this.time = time;
    }

    public String getShowTimeId() {
        return showTimeId;
    }

    public void setShowTimeId(String showTimeId) {
        this.showTimeId = showTimeId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void quanLyPhongChieu() {
        // stub
    }
}

