package com.mina.mail.ru.cinema.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author belski M
 */
@Data
public class FilmDateDto {

    private Integer id;

    private List<FilmTicketDto> tickets;

    @JsonIgnore
    private LocalDateTime dateAndTime;

    private String formattedDate;

}
