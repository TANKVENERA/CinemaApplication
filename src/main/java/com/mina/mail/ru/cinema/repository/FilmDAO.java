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
    List<FilmEntity> getFilmsByTitle(@Param("title") String title);

    @Query("select f from FilmEntity f where f.title=:title")
    FilmEntity getFilmByTitle(@Param("title") String title);

    @Query("select f from FilmEntity f where f.title=:title")
    FilmEntity getFilmTickets(@Param("title") String title);

    @Query("select f from FilmEntity f join f.dates")
    List<FilmEntity> getFilms();

    @Query("select f.id from FilmEntity f where f.title=:title")
    Integer getFilmId(@Param("title")String title);


}
