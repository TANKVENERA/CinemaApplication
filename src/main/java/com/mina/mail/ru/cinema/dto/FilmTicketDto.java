package com.mina.mail.ru.cinema.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mina on 24.04.2019.
 */
@Data
@NoArgsConstructor
public class FilmTicketDto {

    private Integer id;

    private Integer seatNumber;

    private Integer row;

    private Integer visitorId;

    private String ticket;

}
