package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.dbo.UserEntity;
import com.mina.mail.ru.cinema.dto.UserOrder;
import com.mina.mail.ru.cinema.repository.FilmRepository;
import com.mina.mail.ru.cinema.repository.FilmTicketRepository;
import com.mina.mail.ru.cinema.dto.UserSeat;
import com.mina.mail.ru.cinema.repository.UserRepository;
import com.mina.mail.ru.cinema.service.FilmTicketService;

import com.mina.mail.ru.cinema.util.TestPropsLoader;
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

    private static final String USER = TestPropsLoader.testUserFirst;
    private static final String TICKET = TestPropsLoader.ticket;
    private static final String FILM = TestPropsLoader.titleOne;

    @InjectMocks
    private FilmTicketService filmTicketService;

    @Mock
    private FilmTicketRepository filmTicketRepository;
    @Mock
    private UserSeat tickets;
    @Mock
    private FilmRepository filmRepository;
    @Mock
    private UserRepository userRepository;

    private static UserOrder order;
    private static UserEntity userEntity;


    @BeforeClass
    public static void setup() {
        order = new UserOrder();
        order.setFilm(FILM);
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
        doReturn(list).when(filmTicketRepository).getAllOrders(USER);
        final List<UserSeat> tickets = filmTicketService.getOrders(USER);
        verify(filmTicketRepository, times(1)).getAllOrders(USER);
        Assert.assertEquals(list.size(), tickets.size());
    }

    @Test
    public void TestBCreateOrder() {
        doReturn(userEntity).when(userRepository).getUserByName(USER);
        filmTicketService.createOrder(order, USER);
        verify(filmTicketRepository, times(1)).getTicketsById(anyString());
        verify(filmTicketRepository, times(2)).createOrder(anyInt(),anyInt(),anyInt(), anyString());
    }

    @Test
    public void TestCUpdateOrders() {
        order.setTicket(TICKET);
        doReturn(1).when(filmRepository).getFilmId(order.getFilm());
        doReturn(userEntity).when(userRepository).getUserByName(USER);
        filmTicketService.updateOrder(order, USER);
        verify(filmTicketRepository, times(1)).createOrder(10,1,1,TICKET);
        verify(filmTicketRepository, times(1)).createOrder(11,1,1,TICKET);
    }

    @Test
    public void TestDDeleteOrderByTicket() {
        doNothing().when(filmTicketRepository).deleteOrderByTicket(TICKET);
        filmTicketService.deleteOrderByTicket(TICKET);
        verify(filmTicketRepository, times(1)).deleteOrderByTicket(TICKET);
    }

}
