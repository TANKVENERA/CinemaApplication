package com.mina.mail.ru.cinema.controller;

import com.mina.mail.ru.cinema.dbo.FilmEntity;
import com.mina.mail.ru.cinema.dto.FilmDto;
import com.mina.mail.ru.cinema.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by Mina on 29.04.2019.
 */
@RestController
public class FilmController {

    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);
    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public ResponseEntity<List<FilmDto>> getFilms() {
        logger.info("Unique list of films is requested...");
        return ResponseEntity.status(HttpStatus.OK).body(filmService.getFilms());
    }

    @GetMapping(value = "/dates", params = "film")
    public ResponseEntity<FilmDto> dates(@RequestParam(value = "film") String film) {
        logger.info("Film with all dates is requested...");
        return ResponseEntity.status(HttpStatus.OK).body(filmService.getFilmByTitle(film));
    }

    @GetMapping(value = "/dates", params = {"film", "date"})
    public ResponseEntity<FilmDto> filmTickets(@RequestParam(value = "film") String title,
                                               @RequestParam(value = "date") String date) {
        logger.info("Film tickets at certain date are requested...");
        return ResponseEntity.status(HttpStatus.OK).body(filmService.getFilmTickets(title, date));
    }

    @GetMapping(value = "/addfilm", params = {"title", "firstdate"})
    public ResponseEntity<String> addFilm(@RequestParam(value = "title") String title,
                                          @RequestParam(value = "firstdate") String firstDate) throws ParseException {

        return ResponseEntity.status(HttpStatus.OK).body(filmService.addFilm(title, firstDate));
    }

    @GetMapping(value = "/deletefilm", params="title")
    public void deleteFilm (@RequestParam(value = "title") String title) {
        logger.info("Trying to delete film...");
        filmService.deleteFilm(title);
    }
}
