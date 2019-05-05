package com.mina.mail.ru.cinema.service.dto;

import java.util.Set;

/**
 * Created by Mina on 29.04.2019.
 */
public class FilmDto {

    private Integer id;

    private String title;

    private Integer filmdate;

    private Set<FilmTicketDto> tickets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFilmdate() {
        return filmdate;
    }

    public void setFilmdate(Integer filmdate) {
        this.filmdate = filmdate;
    }

    public Set<FilmTicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(Set<FilmTicketDto> tickets) {
        this.tickets = tickets;
    }
}
