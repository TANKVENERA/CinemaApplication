package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.repository.FilmDAO;
import com.mina.mail.ru.cinema.repository.FilmTicketDAO;
import com.mina.mail.ru.cinema.repository.UserDAO;
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
    private FilmTicketDAO filmTicketDAO;
    private FilmDAO filmDAO;
    private UserDAO userDAO;

    @Autowired
    public FilmTicketService(FilmTicketDAO filmTicketDAO, FilmDAO filmDAO, UserDAO userDAO) {
        this.filmTicketDAO = filmTicketDAO;
        this.filmDAO = filmDAO;
        this.userDAO = userDAO;
    }

    public List<UserSeat> getOrders(String login) {
        List<UserSeat> tickets = filmTicketDAO.getAllOrders(login);
        logger.info("Received user orders.");
        return tickets;
    }

    public void createOrder (UserOrder order, String login) {
        Integer filmId = filmDAO.getFilmId(order.getFilm());
        Integer userId = userDAO.getUserByName(login).getId();
        String ticketId;
        for (;;) {
            ticketId = RandomString.make(7);
            List<FilmTicketEntity>  tickets = filmTicketDAO.getTicketsById(ticketId);
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
           filmTicketDAO.createOrder(seat, userId, filmId, ticketId);
        }
        logger.info("Order was created.");
    }

    public void updateOrder (UserOrder order, String login) {
        Integer filmId = filmDAO.getFilmId(order.getFilm());
        Integer userId = userDAO.getUserByName(login).getId();
        List<Integer> seats = order.getSeats();
        deleteOrderByTicket(order.getTicket());
        for (Integer seat : seats) {
            filmTicketDAO.createOrder(seat, userId, filmId, order.getTicket());
        }
        logger.info("Order was updated.");
    }

    public void deleteOrderByTicket (String ticket) {
        filmTicketDAO.deleteOrderByTicket(ticket);
        logger.info("Order was deleted.");
    }

}
