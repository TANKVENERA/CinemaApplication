package com.mina.mail.ru.cinema.dto;

/**
 * Created by Mina on 24.04.2019.
 */
public class FilmTicketDto {

    private Integer id;

    private Integer seatnumber;

    private Integer visitorid;

    public FilmTicketDto() {
    }

    public FilmTicketDto(Integer id, Integer seatnumber, Integer visitorid) {
        this.id = id;
        this.seatnumber = seatnumber;
        this.visitorid = visitorid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(Integer seatnumber) {
        this.seatnumber = seatnumber;
    }

    public Integer getVisitorid() {
        return visitorid;
    }

    public void setVisitorid(Integer visitorid) {
        this.visitorid = visitorid;
    }

    @Override
    public String toString() {
        return "FilmTicketDto{" +
                "id=" + id +
                ", seatnumber=" + seatnumber +
                ", visitorid=" + visitorid +
                '}';
    }
}
