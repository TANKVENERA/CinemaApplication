package com.mina.mail.ru.cinema.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 23.04.2019.
 */
@Data
public class UserDto {

    @JsonIgnore
    private Integer id;
    private String login;
    private String role;

    @JsonIgnore
    private List<FilmTicketDto> tickets = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(final String login, final String role) {
        this.login = login;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public List<FilmTicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(final List<FilmTicketDto> tickets) {
        this.tickets = tickets;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }
}
