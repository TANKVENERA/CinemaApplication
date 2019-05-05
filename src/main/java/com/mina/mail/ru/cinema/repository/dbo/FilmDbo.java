package com.mina.mail.ru.cinema.repository.dbo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mina on 29.04.2019.
 */
@Entity
@Table(name = "film")
public class FilmDbo implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "filmdate")
    private Integer filmdate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Set<FilmTicketDbo> tickets;

    public FilmDbo() {
    }

    public FilmDbo(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFilmdate() {
        return filmdate;
    }

    public void setFilmdate(Integer date) {
        this.filmdate = date;
    }

    public Set<FilmTicketDbo> getTickets() {
        return tickets;
    }

    public void setTickets(Set<FilmTicketDbo> tickets) {
        this.tickets = tickets;
    }
}
