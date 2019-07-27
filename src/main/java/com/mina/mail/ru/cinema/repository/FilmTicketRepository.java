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
public interface FilmTicketRepository extends JpaRepository<FilmTicketEntity, Long> {

    String GET_ALL_ORDERS="SELECT ft.seat, ft.row, f.title, fd.filmdate, fd.id as dateId, ft.ticket FROM filmticket ft join filmdate fd on" +
                                        " fd.id=ft.filmdate_id join user u on u.id=ft.visitor_id join film f on f.id=fd.film_id where u.login=:login order by ft.ticket";

    @Transactional
    @Modifying
    @Query(value = "INSERT into filmticket (seat, row, visitor_id, filmdate_id, ticket) values (:seat, :row, :userId, :filmDateId, :ticket)", nativeQuery = true)
    Integer createOrder(@Param("seat")Integer seat, @Param("row")Integer row, @Param("userId") Integer userId, @Param("filmDateId") Integer filmDateId,
                        @Param("ticket") String ticket);

    @Query(value = GET_ALL_ORDERS, nativeQuery = true)
    List<UserSeat> getAllOrders(@Param("login") String login);

    @Transactional
    @Modifying
    @Query("delete from FilmTicketEntity f where f.ticket=:ticket")
    void deleteOrderByTicket(@Param("ticket") String ticket);

    @Query("select f from FilmTicketEntity f where f.ticket=:ticket")
    List<FilmTicketEntity> getTicketsById(@Param("ticket") String ticket);
}
