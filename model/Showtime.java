package model;

import java.util.*;
import java.util.Date;

public class Showtime {
    private String showTimeId;
    private String movieId;
    private Date date;
    private String room;
    private Map<String, Boolean> seatStatus;

    public Showtime(String showTimeId, String movieId, Date date, String room) {
        this.showTimeId = showTimeId;
        this.movieId = movieId;
        this.date = date;
        this.room = room;
    }

}

