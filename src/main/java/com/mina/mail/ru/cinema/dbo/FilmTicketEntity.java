package com.mina.mail.ru.cinema.dbo;



import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mina on 22.04.2019.
 */

@Entity
@Data
@Table(name = "filmticket")
public class FilmTicketEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "seat")
    private Integer seatnumber;

    @Column(name = "visitor_id")
    private Integer visitorid;

    @Column(name = "ticket")
    private String ticket;

    public FilmTicketEntity() {
    }
}
