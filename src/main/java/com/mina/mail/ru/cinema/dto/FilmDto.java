package com.mina.mail.ru.cinema.dto;

import com.mina.mail.ru.cinema.dbo.FilmDatesEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by Mina on 29.04.2019.
 */
public class FilmDto {

    private Integer id;

    private String title;

    private Set<FilmTicketDto> tickets;

    private List<String> formattedDates;

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

    public Set<FilmTicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(Set<FilmTicketDto> tickets) {
        this.tickets = tickets;
    }

    public List<String> getFormattedDates() {
        return formattedDates;
    }

    public void setFormattedDates(List<String> formattedDates) {
        this.formattedDates = formattedDates;
    }
}
