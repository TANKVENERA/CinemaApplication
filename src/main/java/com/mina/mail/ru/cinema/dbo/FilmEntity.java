package com.mina.mail.ru.cinema.dbo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mina on 29.04.2019.
 */
@Entity
@Table(name = "film")
public class FilmEntity implements Serializable {

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
    private Set<FilmTicketEntity> tickets;

    public FilmEntity() {
    }

    public FilmEntity(String title) {
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

    public Set<FilmTicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(Set<FilmTicketEntity> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "FilmEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", filmdate=" + filmdate +
                ", tickets=" + tickets +
                '}';
    }
}
