package com.mina.mail.ru.cinema.dto;

import java.util.List;
import java.util.Set;

/**
 * Created by Mina on 29.04.2019.
 */
public class FilmDto {

    private Integer id;

    private String title;

    private List<FilmDateDto> dates;


    public FilmDto() {
    }

    public FilmDto(String title) {
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

    public void setDates(List<FilmDateDto> dates) {
        this.dates = dates;
    }
}
