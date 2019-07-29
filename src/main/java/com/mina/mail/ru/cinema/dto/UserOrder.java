package com.mina.mail.ru.cinema.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Mina on 12.05.2019.
 */
@Data
public class UserOrder {

    private String film;
    private List<SeatAndRowDto> seats;
    private Integer dateId;
    private String ticket;
}
