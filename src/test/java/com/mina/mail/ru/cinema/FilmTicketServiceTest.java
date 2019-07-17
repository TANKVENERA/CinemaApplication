package com.mina.mail.ru.cinema;

import ch.qos.logback.core.Appender;
import com.mina.mail.ru.cinema.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.dbo.UserEntity;
import com.mina.mail.ru.cinema.dto.UserOrder;
import com.mina.mail.ru.cinema.repository.FilmDAO;
import com.mina.mail.ru.cinema.repository.FilmTicketDAO;
import com.mina.mail.ru.cinema.dto.UserSeat;
import com.mina.mail.ru.cinema.repository.UserDAO;
import com.mina.mail.ru.cinema.service.FilmTicketService;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by Mina on 21.05.2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class FilmTicketServiceTest {

    private static final String MOCK_USER = "mockUser";
    private static final String MOCK_TICKET = "mockTicket";

    @InjectMocks
    private FilmTicketService filmTicketService;

    @Mock
    private FilmTicketDAO filmTicketDAO;
    @Mock
    private UserSeat tickets;
    @Mock
    private FilmDAO filmDAO;
    @Mock
    private UserDAO userDAO;

    private static UserOrder order;
    private static UserEntity userEntity;


    @BeforeClass
    public static void setup() {
        order = new UserOrder();
        order.setFilm("Test film");
        order.setDateId(1);
        final List<Integer> seats = new ArrayList<>();
        seats.add(10);
        seats.add(11);
        order.setSeats(seats);
        userEntity = new UserEntity();
        userEntity.setId(1);
    }

    @Test
    public void TestAGetOrders(){
        final List<UserSeat> list = new ArrayList<>();
        list.add(tickets);
        list.add(tickets);
        doReturn(list).when(filmTicketDAO).getAllOrders(MOCK_USER);
        final List<UserSeat> tickets = filmTicketService.getOrders(MOCK_USER);
        verify(filmTicketDAO, times(1)).getAllOrders(MOCK_USER);
        Assert.assertEquals(list.size(), tickets.size());
    }

    @Test
    public void TestBCreateOrder() {
        doReturn(1).when(filmDAO).getFilmId(order.getFilm());
        doReturn(userEntity).when(userDAO).getUserByName(MOCK_USER);
        filmTicketService.createOrder(order, MOCK_USER);
        verify(filmTicketDAO, times(1)).getTicketsById(anyString());
        verify(filmTicketDAO, times(2)).createOrder(anyInt(),anyInt(),anyInt(), anyString());
    }

    @Test
    public void TestCUpdateOrders() {
        order.setTicket("ticketId");
        doReturn(1).when(filmDAO).getFilmId(order.getFilm());
        doReturn(userEntity).when(userDAO).getUserByName(MOCK_USER);
        filmTicketService.updateOrder(order, MOCK_USER);
        verify(filmTicketDAO, times(1)).createOrder(10,1,1,"ticketId");
        verify(filmTicketDAO, times(1)).createOrder(11,1,1,"ticketId");
    }

    @Test
    public void TestDDeleteOrderByTicket() {
        doNothing().when(filmTicketDAO).deleteOrderByTicket(MOCK_TICKET);
        filmTicketService.deleteOrderByTicket(MOCK_TICKET);
        verify(filmTicketDAO, times(1)).deleteOrderByTicket(MOCK_TICKET);
    }

}
