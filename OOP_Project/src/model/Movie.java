package model;

public class Movie {
    private String movieId;
    private String title;
    private String description;
    private String genre;

    public Movie() {}

    public Movie(String movieId, String title, String description, String genre) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.genre = genre;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void hienThiThongTin() {
        System.out.println(title + " (" + genre + ")\n" + description);
    }
}

