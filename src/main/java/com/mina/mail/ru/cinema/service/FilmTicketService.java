package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.repository.FilmDAO;
import com.mina.mail.ru.cinema.repository.FilmTicketDAO;
import com.mina.mail.ru.cinema.repository.UserDAO;
import com.mina.mail.ru.cinema.dto.UserOrder;
import com.mina.mail.ru.cinema.dto.UserTickets;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    public void createOrder (UserOrder order, String login) {
        Integer filmId = filmDAO.getFilmId(order.getFilm(), order.getDateIndex()).getId();
        Integer userId = userDAO.getUserByName(login).getId();
        String ticketToken = RandomString.make(7);

        List<Integer> seats = order.getSeats();
        for (Integer seat : seats) {
           filmTicketDAO.createOrder(seat, userId, filmId, ticketToken);
        }
        logger.info("Order was created.");
    }

    public List<UserTickets> getOrders(String login) {
        List<UserTickets> tickets = filmTicketDAO.getAllOrders(login);
        logger.info("Received user orders.");
        return tickets;
    }

    public void deleteOrder (String title, Integer date, Integer seat) {
        filmTicketDAO.deleteOrder(title, date, seat);
        logger.info("Order was deleted.");
    }

}
