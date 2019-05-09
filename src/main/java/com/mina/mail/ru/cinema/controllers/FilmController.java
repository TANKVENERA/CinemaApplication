package com.mina.mail.ru.cinema.controllers;

import com.mina.mail.ru.cinema.repository.dbo.FilmDbo;
import com.mina.mail.ru.cinema.service.dto.FilmDto;
import com.mina.mail.ru.cinema.service.impl.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mina on 29.04.2019.
 */
@RestController
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping("/films")
    public List<FilmDto> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping(value = "/dates", params = "film")
    public List<FilmDto> getDatesByFilm(@RequestParam(value = "film") String film) {
        return filmService.getDatesByFilm(film);
    }
}
