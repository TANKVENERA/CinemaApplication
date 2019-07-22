package com.mina.mail.ru.cinema.dbo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private List<FilmDateEntity> dates;

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

    public List<FilmDateEntity> getDates() {
        return dates;
    }

    public void setDates(List<FilmDateEntity> dates) {
        this.dates = dates;
    }

}
