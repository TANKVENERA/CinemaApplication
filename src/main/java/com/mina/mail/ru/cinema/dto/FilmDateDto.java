package com.mina.mail.ru.cinema.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author belski M
 */
public class FilmDateDto {

    private Integer id;

    private List<FilmTicketDto> tickets;

    @JsonIgnore
    private LocalDateTime dateAndTime;

    private String formattedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<FilmTicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<FilmTicketDto> tickets) {
        this.tickets = tickets;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
}
