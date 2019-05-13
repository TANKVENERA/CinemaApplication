package com.mina.mail.ru.cinema.service.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 23.04.2019.
 */
public class UserDto {

    private Integer id;

    private String login;

    private String role;


    public UserDto() {
    }

    public UserDto(String login, String role) {
        this.login = login;
        this.role = role;
    }

    private List<FilmTicketDto> tickets = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<FilmTicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<FilmTicketDto> tickets) {
        this.tickets = tickets;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
