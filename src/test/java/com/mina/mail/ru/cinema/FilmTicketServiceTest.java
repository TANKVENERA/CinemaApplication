package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.repository.FilmTicketDAO;
import com.mina.mail.ru.cinema.dto.UserSeat;
import com.mina.mail.ru.cinema.service.FilmTicketService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

/**
 * Created by Mina on 21.05.2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class FilmTicketServiceTest {

    @InjectMocks
    private FilmTicketService filmTicketService;

    @Mock
    private FilmTicketDAO filmTicketDAO;
    @Mock
    private UserSeat tickets;

    @Test
    public void TestBGetAllOrders(){
        final List<UserSeat> list = new ArrayList<>();
        list.add(tickets);
        list.add(tickets);
        doReturn(list).when(filmTicketDAO).getAllOrders("mockUser");
        final List<UserSeat> tickets = filmTicketService.getOrders("mockUser");
        verify(filmTicketDAO, times(1)).getAllOrders("mockUser");
        Assert.assertEquals(list.size(), tickets.size());
    }

}
