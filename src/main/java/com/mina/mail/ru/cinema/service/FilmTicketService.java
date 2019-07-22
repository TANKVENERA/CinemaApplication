package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.repository.FilmRepository;
import com.mina.mail.ru.cinema.repository.FilmTicketRepository;
import com.mina.mail.ru.cinema.repository.UserRepository;
import com.mina.mail.ru.cinema.dto.UserOrder;
import com.mina.mail.ru.cinema.dto.UserSeat;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mina on 12.05.2019.
 */
@Service
public class FilmTicketService {

    private static final Logger logger = LoggerFactory.getLogger(FilmTicketService.class);
    private FilmTicketRepository filmTicketRepository;
    private FilmRepository filmRepository;
    private UserRepository userRepository;

    @Autowired
    public FilmTicketService(FilmTicketRepository filmTicketRepository, FilmRepository filmRepository, UserRepository userRepository) {
        this.filmTicketRepository = filmTicketRepository;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    public List<UserSeat> getOrders(String login) {
        final List<UserSeat> tickets = filmTicketRepository.getAllOrders(login);
        logger.info("Received user orders.");
        return tickets;
    }

    public void createOrder (UserOrder order, String login) {
        Integer userId = userRepository.getUserByName(login).getId();
        String ticketId;
        for (;;) {
            ticketId = RandomString.make(7);
            List<FilmTicketEntity>  tickets = filmTicketRepository.getTicketsById(ticketId);
            if (tickets.size() == 0) {
                logger.info("Generated ticket was verified. Start saving order.");
                break;
            }
            else {
                logger.info("Generated ticket already exists. Generating new one...");
            }
        }

        List<Integer> seats = order.getSeats();
        for (Integer seat : seats) {
           filmTicketRepository.createOrder(seat, userId, order.getDateId(), ticketId);
        }
        logger.info("Order was created.");
    }

    public void updateOrder (UserOrder order, String login) {
        Integer filmId = filmRepository.getFilmId(order.getFilm());
        Integer userId = userRepository.getUserByName(login).getId();
        List<Integer> seats = order.getSeats();
        deleteOrderByTicket(order.getTicket());
        for (Integer seat : seats) {
            filmTicketRepository.createOrder(seat, userId, filmId, order.getTicket());
        }
        logger.info("Order was updated.");
    }

    public void deleteOrderByTicket (String ticket) {
        filmTicketRepository.deleteOrderByTicket(ticket);
        logger.info("Order was deleted.");
    }

}
