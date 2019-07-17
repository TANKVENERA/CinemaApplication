package com.mina.mail.ru.cinema.dto;

import java.util.List;

/**
 * Created by Mina on 12.05.2019.
 */
public class UserOrder {

    private String film;
    private List<Integer> seats;
    private Integer dateId;
    private String ticket;

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

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "film='" + film + '\'' +
                ", seats=" + seats +
                ", dateIndex=" + dateId +
                ", ticket='" + ticket + '\'' +
                '}';
    }
}
