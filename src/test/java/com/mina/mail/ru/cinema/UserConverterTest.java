package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.converter.UserConverter;
import com.mina.mail.ru.cinema.dbo.UserEntity;
import com.mina.mail.ru.cinema.dto.UserDto;
import com.mina.mail.ru.cinema.util.TestPropsLoader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;

/**
 * Created by Mina on 21.07.2019.
 */
public class UserConverterTest {

    private final UserConverter converter = new UserConverter();
    private static UserEntity dbo = new UserEntity();
    private static UserDto dto = new UserDto();
    private static final String USER = TestPropsLoader.testUserFirst;


    @BeforeClass
    public static void setUp() throws IOException {
        dbo.setLogin(USER);
        dto.setLogin(USER);
    }

    @Test
    public void testACheckId(){
       UserDto userDto = converter.convertToDto(dbo);
        Assert.assertTrue("Logins are not equal!", userDto.getLogin().equals(USER));
    }

    @Test
    public void testBCheckSeat(){
        UserEntity userEntity = converter.convertToDbo(dto);
        Assert.assertTrue("Seats are not equal!", userEntity.getLogin().equals(USER));
    }

}
