package com.mina.mail.ru.cinema.service.dto;

/**
 * Created by Mina on 24.04.2019.
 */
public class FilmTicketDto {

    private Integer id;

    private Integer seatNumber;

    private Integer visitorId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
    }
}
