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
    private Integer seatnumber;

    @Column(name = "visitor_id")
    private Integer visitorid;

    public FilmTicketDbo() {
    }

    public FilmTicketDbo(Integer id, Integer seatnumber, Integer visitorid) {
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

    public void setSeatnumber(Integer seatNumber) {
        this.seatnumber = seatNumber;
    }

    public Integer getVisitorid() {
        return visitorid;
    }

    public void setVisitorid(Integer visitorId) {
        this.visitorid = visitorId;
    }
}
