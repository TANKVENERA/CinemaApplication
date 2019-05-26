package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.converter.FilmTicketConverter;
import com.mina.mail.ru.cinema.dto.FilmTicketDto;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by Mina on 21.05.2019.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FilmTicketConverterTest {

    private static final FilmTicketConverter converter = new FilmTicketConverter();
    private static FilmTicketEntity dbo = new FilmTicketEntity();

    @BeforeClass
    public static void setUp() {
        dbo.setId(1);
        dbo.setSeatnumber(2);
        dbo.setVisitorid(3);
    }

    @Test
    public void testACheckId(){
        FilmTicketDto dto = converter.convertToDto(dbo);
        Assert.assertTrue("Ids are not equal!", dto.getId().equals(1));
    }

    @Test
    public void testBCheckSeat(){
        FilmTicketDto dto = converter.convertToDto(dbo);
        Assert.assertTrue("Seats are not equal!", dto.getSeatnumber().equals(2));
    }

    @Test
    public void testCChecVisitorID(){
        FilmTicketDto dto = converter.convertToDto(dbo);
        Assert.assertTrue("Visitor Ids are not equal!", dto.getVisitorid().equals(3));
    }
}
