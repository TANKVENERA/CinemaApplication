package com.mina.mail.ru.cinema.repository;

import com.mina.mail.ru.cinema.entity.FilmDateEntity;
import com.mina.mail.ru.cinema.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mina on 29.04.2019.
 */

public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {

    @Query("select f from FilmEntity f where f.title=:title")
    FilmEntity getFilmByTitle(@Param("title") String title);

    @Query("select d from FilmDateEntity d where d.id=:dateId")
    FilmDateEntity getTicketsByDate(@Param("dateId") Integer dateId);

    @Query("select new com.mina.mail.ru.cinema.entity.FilmEntity(f.title)  from FilmEntity f")
    List<FilmEntity> getFilms();

    @Query("select f.id from FilmEntity f join f.dates where f.title=:title")
    Integer getFilmId(@Param("title")String title);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM filmdate where id=:id", nativeQuery = true)
    void deleteDate(@Param("id")Integer id);

}