package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.entity.FilmTicketEntity;
import com.mina.mail.ru.cinema.dto.SeatAndRowDto;
import com.mina.mail.ru.cinema.repository.FilmRepository;
import com.mina.mail.ru.cinema.repository.FilmTicketRepository;
import com.mina.mail.ru.cinema.repository.UserRepository;
import com.mina.mail.ru.cinema.dto.UserOrder;
import com.mina.mail.ru.cinema.dto.UserSeatDto;
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
    private final FilmTicketRepository filmTicketRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    @Autowired
    public FilmTicketService(final FilmTicketRepository filmTicketRepository, final FilmRepository filmRepository, final UserRepository userRepository) {
        this.filmTicketRepository = filmTicketRepository;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }

    public List<UserSeatDto> getOrders(final String login) {
        final List<UserSeatDto> tickets = filmTicketRepository.getAllOrders(login);
        logger.info("Received user orders.");
        return tickets;
    }

    public void createOrder (final UserOrder order, final String login) {
        final Integer userId = userRepository.getUserByName(login).getId();
        String ticketToken;
        for (;;) {
            ticketToken = RandomString.make(7);
            final List<FilmTicketEntity>  tickets = filmTicketRepository.getTicketsById(ticketToken);
            if (tickets.size() == 0) {
                logger.info("Generated ticket was verified. Start saving order.");
                break;
            }
            else {
                logger.info("Generated ticket already exists. Generating new one...");
            }
        }

        List<SeatAndRowDto> seats = order.getSeats();
        for (SeatAndRowDto seatAndRowDto : seats) {
           filmTicketRepository.createOrder(seatAndRowDto.getSeatNmb(), seatAndRowDto.getRowNmb(), userId, order.getDateId(), ticketToken);
        }
        logger.info("Order was created.");
    }

    public void updateOrder (final UserOrder order, final String login) {
        final Integer filmId = filmRepository.getFilmId(order.getFilm());
        final Integer userId = userRepository.getUserByName(login).getId();
        final List<SeatAndRowDto> seats = order.getSeats();
        deleteOrderByTicket(order.getTicket());
        for (SeatAndRowDto seatAndRowDto : seats) {
            filmTicketRepository.createOrder(seatAndRowDto.getSeatNmb(), seatAndRowDto.getRowNmb(), userId, filmId, order.getTicket());
        }
        logger.info("Order was updated.");
    }

    public void deleteOrderByTicket (final String ticket) {
        filmTicketRepository.deleteOrderByTicket(ticket);
        logger.info("Order was deleted.");
    }

}
