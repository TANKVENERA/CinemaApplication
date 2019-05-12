package com.mina.mail.ru.cinema.repository.impl;

import com.mina.mail.ru.cinema.repository.dbo.FilmDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by Mina on 29.04.2019.
 */

public interface FilmDAO extends JpaRepository<FilmDbo, Integer> {

    @Query("select f from FilmDbo f where f.title=:title")
    List<FilmDbo> getDatesByFilm(@Param("title") String title);

    @Query("select new com.mina.mail.ru.cinema.repository.dbo.FilmDbo(f.title) from FilmDbo f group by f.title")
    List<FilmDbo> getFilms();

    @Query("select f from FilmDbo f where f.title=:title and  f.filmdate=:date")
    FilmDbo getFilmId(@Param("title")String title, @Param("date")Integer date);
}
