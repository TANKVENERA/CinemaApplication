package com.mina.mail.ru.cinema.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 23.04.2019.
 */
@Data
@NoArgsConstructor
public class UserDto {

    @JsonIgnore
    private Integer id;
    private String login;
    private String role;

    @JsonIgnore
    private List<FilmTicketDto> tickets = new ArrayList<>();

    public UserDto(final String login, final String role) {
        this.login = login;
        this.role = role;
    }
}
