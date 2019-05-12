package com.mina.mail.ru.cinema.repository.impl;

import com.mina.mail.ru.cinema.repository.dbo.FilmTicketDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mina on 12.05.2019.
 */
public interface FilmTicketDAO extends JpaRepository<FilmTicketDbo, Long> {

    @Transactional
    @Modifying
    @Query(value = "insert into filmticket (seat, visitor_id, film_id) values (:seat, :userId, :filmId)", nativeQuery = true)
    public Integer createOrder(@Param("seat")Integer seat, @Param("userId") Integer userId, @Param("filmId") Integer filmId);
}
