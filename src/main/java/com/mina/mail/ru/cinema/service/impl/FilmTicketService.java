package com.mina.mail.ru.cinema.service.impl;

import com.mina.mail.ru.cinema.repository.dbo.UserDbo;
import com.mina.mail.ru.cinema.repository.impl.FilmDAO;
import com.mina.mail.ru.cinema.repository.impl.FilmTicketDAO;
import com.mina.mail.ru.cinema.repository.impl.UserDAO;
import com.mina.mail.ru.cinema.service.util.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mina on 12.05.2019.
 */
@Service
public class FilmTicketService {

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

        List<Integer> seats = order.getSeats();
        for (Integer seat : seats) {
           filmTicketDAO.createOrder(seat, userId, filmId);
        }
    }

}
