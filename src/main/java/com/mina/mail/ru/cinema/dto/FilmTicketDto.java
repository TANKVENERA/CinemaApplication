package com.mina.mail.ru.cinema.dto;

import lombok.Data;

/**
 * Created by Mina on 24.04.2019.
 */
@Data
public class FilmTicketDto {

    private Integer id;

    private Integer seatnumber;

    private Integer visitorid;

    private String ticket;

    public FilmTicketDto() {
    }

    public FilmTicketDto(Integer id, Integer seatnumber, Integer visitorid) {
        this.id = id;
        this.seatnumber = seatnumber;
        this.visitorid = visitorid;
    }
}
