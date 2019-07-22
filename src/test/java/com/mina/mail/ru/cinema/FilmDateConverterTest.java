package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.converter.FilmConverter;
import com.mina.mail.ru.cinema.converter.FilmDateConverter;
import com.mina.mail.ru.cinema.converter.FilmTicketConverter;
import com.mina.mail.ru.cinema.dbo.FilmDateEntity;
import com.mina.mail.ru.cinema.dbo.FilmEntity;
import com.mina.mail.ru.cinema.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.dto.FilmDateDto;
import com.mina.mail.ru.cinema.dto.FilmDto;
import com.mina.mail.ru.cinema.dto.FilmTicketDto;
import com.mina.mail.ru.cinema.util.TestPropsLoader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 20.07.2019.
 */

public class FilmDateConverterTest {

    private final FilmDateConverter converter = new FilmDateConverter();
    private static FilmDateEntity dbo = new FilmDateEntity();
    private static FilmDateDto dto = new FilmDateDto();
    private static final Integer DATE_ID = TestPropsLoader.dateId;

    @BeforeClass
    public static void setUpStaticData() throws IOException {
        dbo.setId(DATE_ID);
        dto.setId(DATE_ID);
    }

    @Test
    public void testAConvertToDto () {
        FilmDateDto dateDto = converter.convertToDto(dbo);
        Assert.assertEquals("Ticket ids are not equal!", dateDto.getId(), new Integer(DATE_ID));
    }

    @Test
    public void testBConvertToDbo () {
        FilmDateEntity dateEntity = converter.convertToDbo(dto);
        Assert.assertEquals("Ticket ids are not equal!", dateEntity.getId(), new Integer(DATE_ID));
    }
}
