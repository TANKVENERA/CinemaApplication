package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.converter.FilmConverter;
import com.mina.mail.ru.cinema.converter.FilmTicketConverter;
import com.mina.mail.ru.cinema.dbo.FilmEntity;
import com.mina.mail.ru.cinema.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.dto.FilmDto;
import com.mina.mail.ru.cinema.dto.FilmTicketDto;
import com.mina.mail.ru.cinema.repository.FilmDAO;
import com.mina.mail.ru.cinema.service.FilmService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

/**
 * Created by Mina on 13.06.2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class FilmServiceTest {

    @InjectMocks
    private FilmService filmService;
    @Mock
    private FilmDAO filmDAO;
    @Mock
    private FilmConverter filmConverter;
    @Mock
    private FilmTicketConverter ticketConverter;

    private static List<FilmEntity> filmEntities = new ArrayList<>();
    private static Set<FilmTicketEntity> tickets = new HashSet<>();

    @BeforeClass
    public static void setup() {
        FilmTicketEntity ticket1 = new FilmTicketEntity(7);
        FilmTicketEntity ticket2 = new FilmTicketEntity(8);
        tickets.add(ticket1);
        tickets.add(ticket2);
        FilmEntity film1 = new FilmEntity();
        FilmEntity film2 = new FilmEntity();
        film1.setTitle("film1");
        film1.setTickets(tickets);
        film2.setTickets(tickets);
        film2.setTitle("film2");
        filmEntities.add(film1);
        filmEntities.add(film2);
    }

    @Test
    public void TestAGetFilms() {
        doReturn(filmEntities).when(filmDAO).getFilms();
        doReturn(new FilmDto()).when(filmConverter).convertToDto(any());
        List<FilmDto> films = filmService.getFilms();
        verify(filmConverter, times(2)).convertToDto(any());
    }

    @Test
    public void TestBGetFilmsByTitle() {
        doReturn(filmEntities).when(filmDAO).getFilmsByTitle(anyString());
        doReturn(new FilmTicketDto()).when(ticketConverter).convertToDto(any());
        doReturn(new FilmDto()).when(filmConverter).convertToDto(any());
        List<FilmDto> films = filmService.getFilmsByTitle(anyString());
        Assert.assertTrue(films.size() == 2);
    }
}
