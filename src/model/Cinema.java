package model;

public class Cinema {
    private String cinemaId;
    private String name;
    private String address;


    public Cinema(String cinemaId, String name, String address) {
        this.cinemaId = cinemaId;
        this.name = name;
        this.address = address;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return name;
    }
}
