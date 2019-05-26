package com.mina.mail.ru.cinema.controllers;

import com.mina.mail.ru.cinema.service.dto.FilmDto;
import com.mina.mail.ru.cinema.service.impl.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mina on 29.04.2019.
 */
@RestController
public class FilmController {


    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public List<FilmDto> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping(value = "/dates", params = "film")
    public ResponseEntity<List<FilmDto>> getDates(@RequestParam(value = "film") String film) {
        return ResponseEntity.status(HttpStatus.OK).body(filmService.getFilmDates(film));
    }
}
