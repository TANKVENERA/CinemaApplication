package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.converter.FilmTicketConverter;
import com.mina.mail.ru.cinema.dto.FilmDto;
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


    @BeforeClass
    public static void setUp() throws IOException {
        final Integer filmId = Integer.parseInt(TestPropsLoader.loadTestProperty("test.ticket.id"));
        final Integer seatNmb = Integer.parseInt(TestPropsLoader.loadTestProperty("test.seat.one"));
        final Integer userId = Integer.parseInt(TestPropsLoader.loadTestProperty("test.user.id"));
        dbo.setId(filmId);
        dbo.setSeatnumber(seatNmb);
        dto.setVisitorid(userId);
    }

    @Test
    public void testACheckId(){
        FilmTicketDto ticketDto = converter.convertToDto(dbo);
        Assert.assertTrue("Ids are not equal!", ticketDto.getId().equals(1));
    }

    @Test
    public void testBCheckSeat(){
        FilmTicketDto ticketDto = converter.convertToDto(dbo);
        Assert.assertTrue("Seats are not equal!", ticketDto.getSeatnumber().equals(1));
    }

    @Test
    public void testCCheckVisitorID(){
        FilmTicketEntity ticketDbo = converter.convertToDbo(dto);
        Assert.assertTrue("Visitor Ids are not equal!", ticketDbo.getVisitorid().equals(1));
    }
}
