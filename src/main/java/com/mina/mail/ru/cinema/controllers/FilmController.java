package com.mina.mail.ru.cinema.controllers;

import com.mina.mail.ru.cinema.repository.dbo.FilmDbo;
import com.mina.mail.ru.cinema.service.dto.FilmDto;
import com.mina.mail.ru.cinema.service.impl.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<FilmDto> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping("/dates")
    public List<FilmDto> getDatesByFilm() {
        return filmService.getDatesByFilm("Avatar");
    }
}
