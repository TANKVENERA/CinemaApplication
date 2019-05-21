package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.repository.impl.FilmTicketDAO;
import com.mina.mail.ru.cinema.service.dto.UserTickets;
import com.mina.mail.ru.cinema.service.impl.FilmTicketService;
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
    private UserTickets tickets;

    @Test
    public void TestADeleteOrder() {
        doNothing().when(filmTicketDAO).deleteOrder("mockTitle", 1, 1);
        filmTicketService.deleteOrder("mockTitle", 1, 1);
        verify(filmTicketDAO, times(1)).deleteOrder("mockTitle", 1,1);
    }

    @Test
    public void TestBGetAllOrders(){
        final List<UserTickets> list = new ArrayList<>();
        list.add(tickets);
        list.add(tickets);
        doReturn(list).when(filmTicketDAO).getAllOrders("mockUser");
        final List<UserTickets> tickets = filmTicketService.getOrders("mockUser");
        verify(filmTicketDAO, times(1)).getAllOrders("mockUser");
        Assert.assertEquals(list.size(), tickets.size());
    }

}
