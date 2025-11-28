package model;

public class Film {
    private String filmId;
    private String title;
    private String genre;
    private int duration;
    private String description;


    public Film(String filmId, String title, String genre, int duration, String description) {
        this.filmId = filmId;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.description = description;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }


    public String getGenre() {
        return genre;
    }



    public int getDuration() {
        return duration;
    }


    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return title;
    }
}
