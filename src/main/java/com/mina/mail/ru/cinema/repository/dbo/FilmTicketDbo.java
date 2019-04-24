package com.mina.mail.ru.cinema.repository.dbo;



import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mina on 22.04.2019.
 */

@Entity
@Table(name = "filmticket")
public class FilmTicketDbo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "seat")
    private Integer seatNumber;

    @Column(name = "visitor_id")
    private Integer visitorId;

    public FilmTicketDbo() {
    }

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
