package com.mina.mail.ru.cinema.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * Created by Mina on 29.04.2019.
 */
@Data
public class FilmDto {

    private Integer id;

    private String title;

    private List<FilmDateDto> dates;

    public FilmDto() {
    }

    public FilmDto(String title) {
        this.title = title;
    }

}
