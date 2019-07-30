package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.converter.FilmConverter;
import com.mina.mail.ru.cinema.converter.FilmDateConverter;
import com.mina.mail.ru.cinema.converter.FilmTicketConverter;
import com.mina.mail.ru.cinema.entity.FilmDateEntity;
import com.mina.mail.ru.cinema.entity.FilmEntity;
import com.mina.mail.ru.cinema.entity.FilmTicketEntity;
import com.mina.mail.ru.cinema.entity.UserEntity;
import com.mina.mail.ru.cinema.dto.FilmDateDto;
import com.mina.mail.ru.cinema.dto.FilmDto;
import com.mina.mail.ru.cinema.repository.FilmRepository;
import com.mina.mail.ru.cinema.repository.UserRepository;
import com.mina.mail.ru.cinema.service.FilmService;
import com.mina.mail.ru.cinema.util.TestPropsLoader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by Mina on 13.06.2019.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class FilmServiceTest  {

    @InjectMocks
    private FilmService filmService;
    @Mock
    private FilmRepository filmRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private FilmConverter filmConverter;
    @Mock
    private FilmDateConverter dateConverter;
    @Mock
    private FilmTicketConverter ticketConverter;

    private static final String TITLE_ONE = TestPropsLoader.titleOne;
    private static final String TITLE_TWO = TestPropsLoader.titleTwo;
    private static final Integer DATE_ID = TestPropsLoader.dateId;
    private static final String DATE_AND_TIME = TestPropsLoader.time;
    private static final String TIME_FORMAT = TestPropsLoader.timeFormat;
    private static final String TICKET = TestPropsLoader.ticket;
    private static final String ROLE_USER = TestPropsLoader.roleUser;
    private static final String ROLE_ADMIN = TestPropsLoader.roleAdmin;
    private static final String WRONG_TIME_FORMAT = TestPropsLoader.wrongTimeFormat;
    private static final String DATE_ONE = TestPropsLoader.dateOne;
    private static final String DATE_TWO = TestPropsLoader.dateTwo;
    private static final List<FilmEntity> filmEntities = new ArrayList<>();
    private static final List<FilmDateEntity> dates = new ArrayList<>();
    private static final List<FilmTicketEntity> tickets = new ArrayList<>();
    private static final FilmDateDto dateDto =  new FilmDateDto();
    private static final FilmDto filmDto = new FilmDto();


    @BeforeClass
    public static void setup() throws IOException{
        final FilmEntity film1 = new FilmEntity();
        final FilmEntity film2 = new FilmEntity();
        final FilmDateEntity date = new FilmDateEntity();
        final FilmTicketEntity ticket = new FilmTicketEntity();
        ticket.setTicket(TICKET);
        tickets.add(ticket);
        dateDto.setId(DATE_ID);
        dateDto.setDateAndTime(LocalDateTime.parse(DATE_AND_TIME, DateTimeFormatter.ofPattern(TIME_FORMAT)));
        date.setId(DATE_ID);
        date.setTickets(tickets);
        date.setDateAndTime(LocalDateTime.parse(DATE_AND_TIME, DateTimeFormatter.ofPattern(TIME_FORMAT)));
        dates.add(date);
        film1.setTitle(TITLE_ONE);
        film1.setDates(dates);
        film2.setTitle(TITLE_TWO);
        filmEntities.add(film1);
        filmEntities.add(film2);
    }

    @Test
    public void TestAGetFilms() {
        doReturn(filmEntities).when(filmRepository).getFilms();
        final FilmDto filmDto = new FilmDto(TITLE_ONE);
        doReturn(filmDto).when(filmConverter).convertToDto(any());
        final List<FilmDto> result = filmService.getFilms();
        verify(filmConverter, times(2)).convertToDto(any());
        verify(filmRepository, times(1)).getFilms();
        Assert.assertTrue("Wrong film title!", filmEntities.get(0).getTitle().equals(TITLE_ONE));
        Assert.assertEquals("Object films are not equal!", result.get(0), filmDto);
    }

    @Test
    public void TestBGetFilmByTitle() {
        doReturn(filmEntities.get(0)).when(filmRepository).getFilmByTitle(anyString());
        final FilmDto filmDto = new FilmDto(TITLE_ONE);
        doReturn(dateDto).when(dateConverter).convertToDto(any());
        doReturn(filmDto).when(filmConverter).convertToDto(any());
        final FilmDto result = filmService.getFilmByTitle(TITLE_ONE);
        verify(dateConverter, times(1)).convertToDto(any());
        verify(filmConverter, times(1)).convertToDto(any());
        Assert.assertTrue(filmEntities.get(0).getDates().get(0).getId().equals(dateDto.getId()));
        Assert.assertTrue(filmEntities.get(0).getDates().get(0).getDateAndTime().equals(dateDto.getDateAndTime()));
    }

    @Test
    public void TestCGetTicketsByDate() {
       doReturn(dates.get(0)).when(filmRepository).getTicketsByDate(DATE_ID);
       doReturn(dateDto).when(dateConverter).convertToDto(any());
       final FilmDateDto result = filmService.getTicketsByDate(DATE_ID);
       verify(ticketConverter, times(1)).convertToDto(any());
       verify(dateConverter, times(1)).convertToDto(any());
       Assert.assertTrue(result.getId().equals(DATE_ID));

    }

    @Test
    public void TestDDeleteFilmWithNotEnoughPermissions() {
        final Authentication authentication = Mockito.mock(Authentication.class);
        final UserEntity user = new UserEntity();
        user.setRole(ROLE_USER);
        doReturn(user).when(userRepository).getUserByName(any());
        final String result =  filmService.deleteFilm(TITLE_ONE, authentication);
        Assert.assertEquals("Error occured when checking wrong permission!", "Not enough permissions for this action", result);
    }

    @Test
    public void TestEDeleteFilmNotFound() {
        final Authentication authentication = Mockito.mock(Authentication.class);
        final UserEntity user = new UserEntity();
        user.setRole(ROLE_ADMIN);
        doReturn(user).when(userRepository).getUserByName(any());
        doReturn(null).when(filmRepository).getFilmByTitle(TITLE_TWO);
        final String result = filmService.deleteFilm(TITLE_TWO, authentication);
        Assert.assertEquals("Error occured when checking that film was not found!", "Film was not found", result);
    }

    @Test
    public void TestFCheckWrongTimeFormat() throws ParseException {
        filmDto.setFormattedDate(WRONG_TIME_FORMAT);
        final String result = filmService.addFilm(null, filmDto);
        Assert.assertEquals("Error occured when checking wrong time format!", result, "Wrong date pattern, use dd-mm-yyyy");
    }

    @Test
    public void TestGCheckUserRole() throws ParseException{
        filmDto.setFormattedDate(DATE_ONE);
        final Authentication auth = Mockito.mock(Authentication.class);
        final UserEntity user = new UserEntity();
        user.setRole(ROLE_USER);
        doReturn(user).when(userRepository).getUserByName(any());
        final String result = filmService.addFilm(auth, filmDto);
        Assert.assertEquals("Error occured when checking wrong permission!", "Not enough permissions for this action", result);
    }

    @Test
    public void TestHCheckAdminRole() throws ParseException{
        filmDto.setTitle(TITLE_ONE);
        filmDto.setFormattedDate(DATE_TWO);
        final Authentication authentication = Mockito.mock(Authentication.class);
        final UserEntity user = new UserEntity();
        user.setRole(ROLE_ADMIN);
        doReturn(user).when(userRepository).getUserByName(any());
        doReturn(filmEntities.get(0)).when(filmRepository).getFilmByTitle(TITLE_ONE);
        final String result = filmService.addFilm(authentication, filmDto);
        Assert.assertTrue("Error occured when checking that film exists!", result.contains("Date of performance -"));
        verify(filmRepository, times(1)).save(any());
    }

    @Test
    public void TestICheckFilmAndDateExists () throws ParseException {
        filmDto.setTitle(TITLE_ONE);
        filmDto.setFormattedDate(DATE_ONE);
        final Authentication authentication = Mockito.mock(Authentication.class);
        final UserEntity  user = new UserEntity();
        user.setRole(ROLE_ADMIN);
        doReturn(user).when(userRepository).getUserByName(any());
        doReturn(filmEntities.get(0)).when(filmRepository).getFilmByTitle(TITLE_ONE);
        final String result = filmService.addFilm(authentication, filmDto);
        Assert.assertTrue("Error occured when checking that film and date exists!", result.contains("Film with date of performance -"));
    }



}
