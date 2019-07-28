package com.mina.mail.ru.cinema.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Mina on 20.07.2019.
 */
public class TestPropsLoader {

    public static String testUserFirst;
    public static String testUserSecond;
    public static String titleOne;
    public static String titleTwo;
    public static Integer dateId;
    public static String time;
    public static String timeFormat;
    public static String ticket;
    public static Integer filmId;
    public static Integer seatFirst;
    public static Integer userId;

    static {
        try {
            File file = ResourceUtils.getFile("classpath:application_test.properties");
            Properties properties = new Properties();
            properties.load(new FileReader(file));
            testUserFirst = properties.getProperty("test.user.login.first");
            testUserSecond = properties.getProperty("test.user.login.second");
            titleOne = properties.getProperty("test.film.first");
            titleTwo = properties.getProperty("test.film.second");
            time = properties.getProperty("test.time");
            dateId = Integer.parseInt(properties.getProperty("test.date.id"));
            timeFormat = properties.getProperty("test.time.format");
            ticket = properties.getProperty("test.ticket.token");
            filmId = Integer.parseInt(properties.getProperty("test.film.id"));
            seatFirst = Integer.parseInt(properties.getProperty("test.seat.one"));
            userId = Integer.parseInt(properties.getProperty("test.user.id"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
