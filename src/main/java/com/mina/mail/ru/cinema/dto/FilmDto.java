package com.mina.mail.ru.cinema.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * Created by Mina on 29.04.2019.
 */
@Data
@NoArgsConstructor
public class FilmDto {

    private Integer id;

    private String title;

    private List<FilmDateDto> dates;

    public FilmDto(final String title) {
        this.title = title;
    }

}
