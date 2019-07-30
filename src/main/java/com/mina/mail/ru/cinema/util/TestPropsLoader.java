package com.mina.mail.ru.cinema.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
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
    public static String roleUser;
    public static String roleAdmin;
    public static String wrongTimeFormat;
    public static String dateOne;
    public static String dateTwo;

    static {
        try {
            File file = ResourceUtils.getFile("classpath:application_test.properties");
            Properties properties = new Properties();
            properties.load(new FileReader(file));
            testUserFirst = properties.getProperty("test.user.login.first");
            testUserSecond = properties.getProperty("test.user.login.second");
            titleOne = properties.getProperty("test.film.first");
            titleTwo = properties.getProperty("test.film.second");
            time = properties.getProperty("test.date.and.time");
            dateId = Integer.parseInt(properties.getProperty("test.date.id"));
            timeFormat = properties.getProperty("test.time.format");
            ticket = properties.getProperty("test.ticket.token");
            filmId = Integer.parseInt(properties.getProperty("test.film.id"));
            seatFirst = Integer.parseInt(properties.getProperty("test.seat.one"));
            userId = Integer.parseInt(properties.getProperty("test.user.id"));
            roleUser = properties.getProperty("test.user.role.user");
            roleAdmin = properties.getProperty("test.user.role.admin");
            wrongTimeFormat = properties.getProperty("test.time.wrong.format");
            dateOne = properties.getProperty("test.date.one");
            dateTwo = properties.getProperty("test.date.two");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
