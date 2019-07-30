package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.entity.FilmEntity;
import com.mina.mail.ru.cinema.converter.FilmConverter;
import com.mina.mail.ru.cinema.dto.FilmDto;

import com.mina.mail.ru.cinema.util.TestPropsLoader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by Mina on 20.05.2019.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FilmConverterTest  {

    private final FilmConverter converter = new FilmConverter();
    private static FilmEntity dbo = new FilmEntity();
    private static FilmDto dto = new FilmDto();
    private static final String TITLE_ONE = TestPropsLoader.titleOne;

    @BeforeClass
    public static void setUpStaticData() {
        dbo.setTitle(TITLE_ONE);
        dto.setTitle(TITLE_ONE);
    }

    @Test
    public void testAConvertToDto () {
        final FilmDto dto = converter.convertToDto(dbo);
        Assert.assertEquals("Titles are not equal!", dto.getTitle(), TITLE_ONE);
    }

    @Test
    public void testBConvertToDbo () {
        final FilmEntity filmEntity = converter.convertToDbo(dto);
        Assert.assertEquals("Titles are not equal!", filmEntity.getTitle(), TITLE_ONE);
    }
}
