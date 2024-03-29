package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.converter.UserConverter;
import com.mina.mail.ru.cinema.entity.UserEntity;
import com.mina.mail.ru.cinema.dto.UserDto;
import com.mina.mail.ru.cinema.util.TestPropsLoader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

/**
 * Created by Mina on 21.07.2019.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserConverterTest {

    private final UserConverter converter = new UserConverter();
    private static final UserEntity dbo = new UserEntity();
    private static final UserDto dto = new UserDto();
    private static final String USER = TestPropsLoader.testUserFirst;


    @BeforeClass
    public static void setUp() throws IOException {
        dbo.setLogin(USER);
        dto.setLogin(USER);
    }

    @Test
    public void testACheckId(){
       final UserDto userDto = converter.convertToDto(dbo);
        Assert.assertTrue("Logins are not equal!", userDto.getLogin().equals(USER));
    }

    @Test
    public void testBCheckSeat(){
        UserEntity userEntity = converter.convertToDbo(dto);
        Assert.assertTrue("Seats are not equal!", userEntity.getLogin().equals(USER));
    }

}
