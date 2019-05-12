package com.mina.mail.ru.cinema.service.util;

import java.util.List;

/**
 * Created by Mina on 12.05.2019.
 */
public class UserOrder {

    private String film;
    private List<Integer> seats;
    private Integer dateIndex;

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }

    public Integer getDateIndex() {
        return dateIndex;
    }

    public void setDateIndex(Integer dateIndex) {
        this.dateIndex = dateIndex;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                ", film='" + film + '\'' +
                ", seats=" + seats +
                ", dateIndex=" + dateIndex +
                '}';
    }
}
