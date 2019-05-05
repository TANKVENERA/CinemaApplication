package com.mina.mail.ru.cinema.repository.dbo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    @Column(name = "filmtitle")
    private String filmtitle;

    @Column(name = "filmdate")
    private Integer filmdate;

    public FilmDbo() {
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Set<FilmTicketDbo> filmtickets;

    public FilmDbo(String filmtitle, Integer filmdate, Set<FilmTicketDbo> filmtickets) {
        this.filmtitle = filmtitle;
        this.filmdate = filmdate;
        this.filmtickets = filmtickets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilmtitle() {
        return filmtitle;
    }

    public void setFilmtitle(String title) {
        this.filmtitle = title;
    }

    public Integer getFilmdate() {
        return filmdate;
    }

    public void setFilmdate(Integer date) {
        this.filmdate = date;
    }

    public Set<FilmTicketDbo> getFilmtickets() {
        return filmtickets;
    }

    public void setFilmtickets(Set<FilmTicketDbo> tickets) {
        this.filmtickets = tickets;
    }
}
