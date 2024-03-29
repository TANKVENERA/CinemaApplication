package com.mina.mail.ru.cinema.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mina.mail.ru.cinema.controller.FilmController;
import com.mina.mail.ru.cinema.dto.UserDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Mina on 25.05.2019.
 */

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationEntryPoint.class);

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException ex) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        logger.warn("User is invalid or not authorized in system...");
        UserDto user = new UserDto();
        user.setLogin("");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        mapper.writeValue(writer, user);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("REALM");
        super.afterPropertiesSet();
    }
}
