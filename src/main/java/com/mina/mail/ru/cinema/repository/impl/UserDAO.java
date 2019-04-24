package com.mina.mail.ru.cinema.repository.impl;

import com.mina.mail.ru.cinema.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mina on 22.04.2019.
 */

public interface UserDAO extends JpaRepository<User, Long> {


}
