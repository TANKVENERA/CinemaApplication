package com.mina.mail.ru.cinema.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mina.mail.ru.cinema.dto.UserDto;

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

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        UserDto user = new UserDto();
        user.setLogin("");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        mapper.writeValue(writer, user);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("YOUR REALM");
        super.afterPropertiesSet();
    }
}
