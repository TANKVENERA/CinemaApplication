package com.mina.mail.ru.cinema.repository.impl;

import com.mina.mail.ru.cinema.repository.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.service.dto.UserTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mina on 12.05.2019.
 */
public interface FilmTicketDAO extends JpaRepository<FilmTicketEntity, Long> {

    static final String GET_ALL_ORDERS="SELECT ft.seat, f.title, f.filmdate FROM filmticket ft join film f on f.id=ft.film_id join user u on u.id=ft.visitor_id where u.login=:login";

    @Transactional
    @Modifying
    @Query(value = "INSERT into filmticket (seat, visitor_id, film_id) values (:seat, :userId, :filmId)", nativeQuery = true)
    Integer createOrder(@Param("seat")Integer seat, @Param("userId") Integer userId, @Param("filmId") Integer filmId);

    @Query(value = GET_ALL_ORDERS, nativeQuery = true)
    List<UserTickets> getAllOrders(@Param("login") String login);

    @Transactional
    @Modifying
    @Query(value = "DELETE ft from filmticket ft join film f on f.id=ft.film_id where f.title=:title and f.filmdate=:date and ft.seat=:seat", nativeQuery = true)
    void deleteOrder(@Param("title") String title, @Param("date") Integer date, @Param("seat") Integer seat);
}
