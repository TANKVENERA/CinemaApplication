package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.entity.UserEntity;
import com.mina.mail.ru.cinema.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Mina on 07.05.2019.
 */

@Service
public class AppUserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AppUserService.class);
    private final UserRepository userRepository;

    @Autowired
    public AppUserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public final UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        final UserEntity currentUser = userRepository.getUserByName(login);
        UserBuilder builder;
        logger.info("Trying to sign in. Comparing user credentials...");
        if (currentUser != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(currentUser.getLogin());
            builder.password("{noop}1");
            builder.roles(currentUser.getRole());
            logger.info("User is successfully signed in...");
        } else {
            logger.error("Invalid user");
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}
