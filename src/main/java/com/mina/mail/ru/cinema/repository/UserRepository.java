package com.mina.mail.ru.cinema.repository;

import com.mina.mail.ru.cinema.dbo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Mina on 22.04.2019.
 */

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select u from UserEntity u where u.login=:login")
    UserEntity getUserByName(@Param("login") String login);

}