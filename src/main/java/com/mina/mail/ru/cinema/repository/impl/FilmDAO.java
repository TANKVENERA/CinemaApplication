package com.mina.mail.ru.cinema.repository.impl;

import com.mina.mail.ru.cinema.repository.dbo.FilmDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mina on 29.04.2019.
 */

public interface FilmDAO extends JpaRepository<FilmDbo, Integer> {

    @Query("select f from FilmDbo f where f.filmtitle=:film")
    List<FilmDbo> getDatesByFilm(@Param("film") String film);
}
