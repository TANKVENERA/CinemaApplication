package com.mina.mail.ru.cinema.repository.dbo;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mina on 22.04.2019.
 */

@Entity
@Table(name = "user")
public class UserDbo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "login")
    private String login;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "visitor_id")
    private List<FilmTicketDbo> tickets = new ArrayList<>();

    public UserDbo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<FilmTicketDbo> getTickets() {
        return tickets;
    }

    public void setTickets(List<FilmTicketDbo> tickets) {
        this.tickets = tickets;
    }
}
