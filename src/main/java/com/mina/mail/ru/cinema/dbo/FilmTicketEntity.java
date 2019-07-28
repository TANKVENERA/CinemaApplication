package com.mina.mail.ru.cinema.dbo;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mina on 22.04.2019.
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "filmticket")
public class FilmTicketEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "seat")
    private Integer seatNumber;

    @Column(name = "row")
    private Integer row;

    @Column(name = "visitor_id")
    private Integer visitorId;

    @Column(name = "ticket")
    private String ticket;

}
