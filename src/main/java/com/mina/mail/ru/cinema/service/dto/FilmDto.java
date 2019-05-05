package com.mina.mail.ru.cinema.service.dto;

import java.util.Set;

/**
 * Created by Mina on 29.04.2019.
 */
public class FilmDto {

    private Integer id;

    private String filmtitle;

    private Integer filmdate;

    private Set<FilmTicketDto> filmtickets;

    public FilmDto() {
    }

    public FilmDto(String filmtitle, Integer filmdate, Set<FilmTicketDto> filmtickets) {
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

    public void setFilmtitle(String filmtitle) {
        this.filmtitle = filmtitle;
    }

    public Integer getFilmdate() {
        return filmdate;
    }

    public void setFilmdate(Integer filmdate) {
        this.filmdate = filmdate;
    }

    public Set<FilmTicketDto> getFilmtickets() {
        return filmtickets;
    }

    public void setFilmtickets(Set<FilmTicketDto> filmtickets) {
        this.filmtickets = filmtickets;
    }
}
