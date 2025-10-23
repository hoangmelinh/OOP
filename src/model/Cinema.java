package model;

public class Cinema {
    private String cinemaId;
    private String name;
    private String address;

    public Cinema() {}

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

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void hienThiThongTin() {
        System.out.println(name + " - " + address);
    }
}

