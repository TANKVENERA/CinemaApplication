package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.converter.UserConverter;
import com.mina.mail.ru.cinema.dbo.UserEntity;
import com.mina.mail.ru.cinema.dto.UserDto;
import com.mina.mail.ru.cinema.repository.UserRepository;
import com.mina.mail.ru.cinema.service.UserService;
import com.mina.mail.ru.cinema.util.TestPropsLoader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
    private UserRepository userRepository;
    @Mock
    private UserConverter userConverter;

    private static final String USER = TestPropsLoader.testUserFirst;
    private static final String USER_FIRST = TestPropsLoader.testUserFirst;
    private static final String USER_SECOND = TestPropsLoader.testUserSecond;
    private static final Integer USER_ID = TestPropsLoader.userId;
    private static List<UserEntity> userEntities = new ArrayList<>();

    @BeforeClass
    public static void setup() {
        UserEntity user1 = new UserEntity();
        user1.setLogin(USER_FIRST);
        UserEntity user2 = new UserEntity();
        user2.setLogin(USER_SECOND);
        userEntities.add(user1);
        userEntities.add(user2);
    }

    @Test
    public void TestAGetAllUsers() {
        doReturn(userEntities).when(userRepository).findAll();
        for (UserEntity u : userEntities) {
            UserDto userDto = new UserDto();
            userDto.setLogin(u.getLogin());
            doReturn(userDto).when(userConverter).convertToDto(u);
        }
        final List<UserDto> users = userService.getAllUsers();

        verify(userRepository, times(1)).findAll();
        verify(userConverter, times(2)).convertToDto(any());
        Assert.assertEquals(userEntities.get(1).getLogin(), users.get(1).getLogin());
        Assert.assertEquals(userEntities.size(), users.size());
    }

    @Test
    public void TestBGetUser() {
        doReturn(userEntities.get(0)).when(userRepository).getUserByName(anyString());
        UserDto userDto = new UserDto();
        userDto.setLogin(userEntities.get(0).getLogin());
        doReturn(userDto).when(userConverter).convertToDto(any());
        final UserDto user = userService.getUser(anyString());
        verify(userConverter, times(1)).convertToDto(any());
        Assert.assertTrue(user.getLogin() == USER_FIRST);
    }

    /** User already exists case **/
    @Test
    public void TestCCreateUser() {
        doReturn(userEntities.get(0)).when(userConverter).convertToDbo(any());
        doReturn(userEntities.get(0)).when(userRepository).getUserByName(anyString());
       final String result = userService.createUser(userEntities.get(0).getLogin());
        Assert.assertTrue(result == "User already exists!" );
    }

    /** User successfully saved case **/
    @Test
    public void TestDCreateUser() {
        doReturn(userEntities.get(0)).when(userConverter).convertToDbo(any());
        doReturn(null).when(userRepository).getUserByName(anyString());
       final String result = userService.createUser(userEntities.get(0).getLogin());
        Assert.assertTrue(result == "User was created successfully!" );
        verify(userRepository, times(1)).save(any());
    }

    @Test

    public void TestEDeleteUser() {
        doNothing().when(userRepository).deleteById(any());
        userRepository.deleteById(any());
        verify(userRepository, times(1)).deleteById(any());
    }

    @Test
    @WithMockUser
    public void TestFCheckAuthNotNull() {
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn(USER);
       final UserDto result = userService.checkAuthentication(authentication);
       Assert.assertTrue(result.getLogin() == USER);
    }

    @Test
    public void TestGCheckAuthNull() {
        final UserDto result = userService.checkAuthentication(null);
        Assert.assertTrue(result.getLogin() == "");
    }

    @Test
    public void TestHLogout(){
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getSession()).thenReturn(null);
        System.out.println(request);
        Mockito.when(request.getCookies()).thenReturn(new Cookie[]{new Cookie("1", "111")});
        final UserDto result = userService.logout(request);
        verify(request, times(1)).getSession(false);
        Assert.assertTrue(result.getLogin() == "");
    }

}
