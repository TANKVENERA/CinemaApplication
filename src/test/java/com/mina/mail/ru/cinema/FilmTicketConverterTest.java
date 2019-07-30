package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.entity.FilmTicketEntity;
import com.mina.mail.ru.cinema.converter.FilmTicketConverter;
import com.mina.mail.ru.cinema.dto.FilmTicketDto;
import com.mina.mail.ru.cinema.util.TestPropsLoader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

/**
 * Created by Mina on 21.05.2019.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FilmTicketConverterTest {

    private final FilmTicketConverter converter = new FilmTicketConverter();
    private static FilmTicketEntity dbo = new FilmTicketEntity();
    private static FilmTicketDto dto = new FilmTicketDto();
    private static final Integer FILM_ID = TestPropsLoader.filmId;
    private static final Integer SEAT = TestPropsLoader.seatFirst;
    private static final Integer USER_ID = TestPropsLoader.userId;


    @BeforeClass
    public static void setUp() throws IOException {
        dbo.setId(FILM_ID);
        dbo.setSeatNumber(SEAT);
        dto.setVisitorId(USER_ID);
    }

    @Test
    public void testACheckId(){
        final FilmTicketDto ticketDto = converter.convertToDto(dbo);
        Assert.assertTrue("Ids are not equal!", ticketDto.getId().equals(1));
    }

    @Test
    public void testBCheckSeat(){
        final FilmTicketDto ticketDto = converter.convertToDto(dbo);
        Assert.assertTrue("Seats are not equal!", ticketDto.getSeatNumber().equals(SEAT));
    }

    @Test
    public void testCCheckVisitorID(){
        final FilmTicketEntity ticketDbo = converter.convertToDbo(dto);
        Assert.assertTrue("Visitor Ids are not equal!", ticketDbo.getVisitorId().equals(USER_ID));
    }
}
