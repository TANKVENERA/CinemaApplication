package com.mina.mail.ru.cinema;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CinemaApplication {

	public static void main(final String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}


}
