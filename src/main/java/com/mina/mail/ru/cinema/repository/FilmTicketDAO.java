package com.mina.mail.ru.cinema.repository;

import com.mina.mail.ru.cinema.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.dto.UserSeat;
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

    String GET_ALL_ORDERS="SELECT ft.seat, f.title, f.filmdate, ft.ticket FROM filmticket ft join film f on" +
                                        " f.id=ft.film_id join user u on u.id=ft.fkvisitor_id where u.login=:login order by ft.ticket";

    @Transactional
    @Modifying
    @Query(value = "INSERT into filmticket (seat, fkvisitor_id, film_id, ticket) values (:seat, :userId, :filmId, :ticket)", nativeQuery = true)
    Integer createOrder(@Param("seat")Integer seat, @Param("userId") Integer userId, @Param("filmId") Integer filmId,
                        @Param("ticket") String ticket);

    @Query(value = GET_ALL_ORDERS, nativeQuery = true)
    List<UserSeat> getAllOrders(@Param("login") String login);

    @Query("select f.id from FilmTicketEntity f where f.ticket=:ticket and f.seatnumber=:seat")
    Integer getSeat(@Param("ticket") String ticket, @Param("seat") Integer seat);

    @Transactional
    @Modifying
    @Query("delete from FilmTicketEntity f where f.ticket=:ticket")
    void deleteOrderByTicket(@Param("ticket") String ticket);

    @Query("select f from FilmTicketEntity f where f.ticket=:ticket")
    List<FilmTicketEntity> getTicketsById(@Param("ticket") String ticket);
}
