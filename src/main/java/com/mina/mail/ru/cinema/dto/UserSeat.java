package com.mina.mail.ru.cinema.dto;

import java.time.LocalDateTime;

/**
 * Created by Mina on 13.05.2019.
 */

public interface UserSeat {

    Integer getSeat();
    String getTitle();
    String getFilmDate();
    String getTicket();
    void setFilmDate();
}
