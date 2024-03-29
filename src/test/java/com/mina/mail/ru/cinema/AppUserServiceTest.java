package com.mina.mail.ru.cinema;

import com.mina.mail.ru.cinema.entity.UserEntity;
import com.mina.mail.ru.cinema.repository.UserRepository;
import com.mina.mail.ru.cinema.service.AppUserService;
import com.mina.mail.ru.cinema.util.TestPropsLoader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * @author belski M
 */


@RunWith(MockitoJUnitRunner.class)
public class AppUserServiceTest {

    @InjectMocks
    private AppUserService appUserService;
    @Mock
    private UserRepository userRepository;

    private static final String USER = TestPropsLoader.testUserFirst;

    @Test(expected = UsernameNotFoundException.class)
    public void TestACheckUserIsNull() {
       doReturn(null).when(userRepository).getUserByName(any());
       appUserService.loadUserByUsername(any());
    }

    @Test
    public void TestBCheckUserIsNotNull() {
        final UserEntity user = new UserEntity();
        user.setLogin(USER);
        user.setRole("USER");
        doReturn(user).when(userRepository).getUserByName(any());
        final UserDetails result =  appUserService.loadUserByUsername(any());
        Assert.assertTrue("User login is wrong!", result.getUsername().equals(USER));
        Assert.assertTrue("User password is wrong", result.getPassword().equals("{noop}1"));

    }

}
