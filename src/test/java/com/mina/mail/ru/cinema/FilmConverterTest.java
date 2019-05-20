package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.repository.dbo.FilmEntity;
import com.mina.mail.ru.cinema.service.converter.FilmConverter;
import com.mina.mail.ru.cinema.service.dto.FilmDto;
import com.mina.mail.ru.cinema.service.dto.FilmTicketDto;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Mina on 20.05.2019.
 */

public class FilmConverterTest {
    private static final String filmTitle = "SPIDER_MAN";
    private final FilmConverter converter = new FilmConverter();
    private static FilmEntity dbo = new FilmEntity();

    @BeforeClass
    public static void setUpStaticData(){
        dbo.setTitle(filmTitle);
        FilmTicketDto ticket = new FilmTicketDto();
    }

    @Test
    public void testAConvertToDto () {
        FilmDto dto = converter.convertToDto(dbo);
        Assert.assertEquals("Titles are not equal!", dto.getTitle(), "SPIDER_MAN");
    }

}
