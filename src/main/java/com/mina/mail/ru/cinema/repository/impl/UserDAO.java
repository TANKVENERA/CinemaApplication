package com.mina.mail.ru.cinema.repository.impl;

import com.mina.mail.ru.cinema.repository.dbo.FilmDbo;
import com.mina.mail.ru.cinema.repository.dbo.UserDbo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mina on 22.04.2019.
 */

public interface UserDAO extends JpaRepository<UserDbo, Long> {

    @Query("select u from UserDbo u where u.login=:login")
    UserDbo getUserByName(@Param("login") String login);

}
