package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.dbo.FilmEntity;
import com.mina.mail.ru.cinema.converter.FilmConverter;
import com.mina.mail.ru.cinema.dto.FilmDto;

import com.mina.mail.ru.cinema.util.TestPropsLoader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by Mina on 20.05.2019.
 */

public class FilmConverterTest  {

    private final FilmConverter converter = new FilmConverter();
    private static FilmEntity dbo = new FilmEntity();
    private static FilmDto dto = new FilmDto();

    @BeforeClass
    public static void setUpStaticData() throws IOException {
      final String title = TestPropsLoader.loadTestProperty("test.film");
        dbo.setTitle(title);
        dto.setTitle(title);
    }

    @Test
    public void testAConvertToDto () {
        FilmDto dto = converter.convertToDto(dbo);
        Assert.assertEquals("Titles are not equal!", dto.getTitle(), "Armageddon");
    }

    @Test
    public void testBConvertToDbo () {
        FilmEntity filmEntity = converter.convertToDbo(dto);
        Assert.assertEquals("Titles are not equal!", filmEntity.getTitle(), "Armageddon");
    }

}
