package com.mina.mail.ru.cinema.dbo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private Set<FilmTicketEntity> tickets;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private List<FilmDatesEntity> dates;

    public FilmEntity() {
    }

    public FilmEntity(Integer id, String title) {
        this.id = id;
        this.title = title;
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

    public Set<FilmTicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(Set<FilmTicketEntity> tickets) {
        this.tickets = tickets;
    }

    public List<FilmDatesEntity> getDates() {
        return dates;
    }

    public void setDates(List<FilmDatesEntity> dates) {
        this.dates = dates;
    }

    @Override
    public String toString() {
        return "FilmEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}
