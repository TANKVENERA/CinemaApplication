package com.mina.mail.ru.cinema.repository;

import com.mina.mail.ru.cinema.dbo.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mina on 29.04.2019.
 */

public interface FilmDAO extends JpaRepository<FilmEntity, Integer> {

    @Query("select f from FilmEntity f where f.title=:title")
    List<FilmEntity> getFilmDates(@Param("title") String title);

    @Query("select new com.mina.mail.ru.cinema.dbo.FilmEntity(f.title) from FilmEntity f group by f.title")
    List<FilmEntity> getFilms();

    @Query("select f from FilmEntity f where f.title=:title and  f.filmdate=:date")
    FilmEntity getFilmId(@Param("title")String title, @Param("date")Integer date);
}
