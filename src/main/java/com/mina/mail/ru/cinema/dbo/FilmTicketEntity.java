package com.mina.mail.ru.cinema.dbo;



import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mina on 22.04.2019.
 */

@Entity
@Table(name = "filmticket")
public class FilmTicketEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "seat")
    private Integer seatnumber;

    @Column(name = "fkvisitor_id")
    private Integer visitorid;

    public FilmTicketEntity() {
    }

    public FilmTicketEntity(Integer id, Integer seatnumber, Integer visitorid) {
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

    @Override
    public String toString() {
        return "FilmTicketEntity{" +
                "id=" + id +
                ", seatnumber=" + seatnumber +
                ", visitorid=" + visitorid +
                '}';
    }
}
