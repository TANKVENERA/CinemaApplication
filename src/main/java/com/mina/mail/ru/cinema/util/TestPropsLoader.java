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

    public static String loadTestProperty (String key) throws IOException {
        File file = ResourceUtils.getFile("classpath:application_test.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        return properties.getProperty(key);
    }
}
