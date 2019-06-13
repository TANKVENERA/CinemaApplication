package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.dbo.FilmEntity;
import com.mina.mail.ru.cinema.converter.FilmConverter;
import com.mina.mail.ru.cinema.dto.FilmDto;
import com.mina.mail.ru.cinema.dto.FilmTicketDto;
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
    private static FilmDto dto = new FilmDto();

    @BeforeClass
    public static void setUpStaticData(){
        dbo.setTitle(filmTitle);
        dto.setTitle(filmTitle);
    }

    @Test
    public void testAConvertToDto () {
        FilmDto dto = converter.convertToDto(dbo);
        Assert.assertEquals("Titles are not equal!", dto.getTitle(), "SPIDER_MAN");
    }

    @Test
    public void testBConvertToDbo () {
        FilmEntity filmEntity = converter.convertToDbo(dto);
        Assert.assertEquals("Titles are not equal!", filmEntity.getTitle(), "SPIDER_MAN");
    }

}
