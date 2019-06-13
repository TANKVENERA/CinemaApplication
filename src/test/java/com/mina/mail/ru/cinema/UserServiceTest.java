package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.converter.UserConverter;
import com.mina.mail.ru.cinema.dbo.UserEntity;
import com.mina.mail.ru.cinema.dto.UserDto;
import com.mina.mail.ru.cinema.repository.UserDAO;
import com.mina.mail.ru.cinema.service.UserService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 13.06.2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserDAO userDAO;
    @Mock
    private UserConverter userConverter;

    private static List<UserEntity> userEntities = new ArrayList<>();

    @BeforeClass
    public static void setup() {
        UserEntity user1 = new UserEntity();
        user1.setLogin("testLogin1");
        UserEntity user2 = new UserEntity();
        user2.setLogin("testLogin2");
        userEntities.add(user1);
        userEntities.add(user2);
    }

    @Test
    public void TestAGetAllUsers() {
        doReturn(userEntities).when(userDAO).findAll();
        for (UserEntity u : userEntities) {
            UserDto userDto = new UserDto();
            userDto.setLogin(u.getLogin());
            doReturn(userDto).when(userConverter).convertToDto(u);

        }
        final List<UserDto> users = userService.getAllUsers();

        verify(userDAO, times(1)).findAll();
        verify(userConverter, times(2)).convertToDto(any());
        Assert.assertEquals(userEntities.get(1).getLogin(), users.get(1).getLogin());
        Assert.assertEquals(userEntities.size(), users.size());
    }

    @Test
    public void TestBGetUser() {
        doReturn(userEntities.get(0)).when(userDAO).getUserByName(anyString());
        UserDto userDto = new UserDto();
        userDto.setLogin(userEntities.get(0).getLogin());
        doReturn(userDto).when(userConverter).convertToDto(any());
        final UserDto user = userService.getUser(anyString());
        verify(userConverter, times(1)).convertToDto(any());
        Assert.assertTrue(user.getLogin() == "testLogin1");
    }

    /** User already exists case **/
    @Test
    public void TestCCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setLogin(userEntities.get(0).getLogin());
        doReturn(userEntities.get(0)).when(userConverter).convertToDbo(any());
        doReturn(userEntities.get(0)).when(userDAO).getUserByName(anyString());
        String result = userService.createUser(userDto);
        Assert.assertTrue(result == "User already exists!" );
    }

    /** User successfully saved case **/
    @Test
    public void TestDCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setLogin(userEntities.get(0).getLogin());
        doReturn(userEntities.get(0)).when(userConverter).convertToDbo(userDto);
        doReturn(null).when(userDAO).getUserByName(anyString());
        String result = userService.createUser(userDto);
        Assert.assertTrue(result == "User was created successfully!" );
        verify(userDAO, times(1)).save(userEntities.get(0));
    }

}
