package com.mina.mail.ru.cinema.controller;

import com.mina.mail.ru.cinema.dbo.FilmEntity;
import com.mina.mail.ru.cinema.dto.FilmDateDto;
import com.mina.mail.ru.cinema.dto.FilmDto;
import com.mina.mail.ru.cinema.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    public FilmController(final FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public ResponseEntity<List<FilmDto>> getFilms() {
        logger.info("Unique list of films is requested...");
        return ResponseEntity.status(HttpStatus.OK).body(filmService.getFilms());
    }

    @GetMapping(value = "/dates", params = "film")
    public ResponseEntity<FilmDto> dates(@RequestParam(value = "film") final String film) {
        logger.info("Film with all dates is requested...");
        return ResponseEntity.status(HttpStatus.OK).body(filmService.getFilmByTitle(film));
    }

    @GetMapping(value = "/ticketsOnDate", params = "dateId")
    public ResponseEntity<FilmDateDto> ticketsAtOneDate(@RequestParam(value = "dateId") final Integer dateId) {
        logger.info("Film tickets at certain dateToUpdate are requested...");
        return ResponseEntity.status(HttpStatus.OK).body(filmService.getTicketsByDate(dateId));
    }

    @PostMapping(value = "/addFilm")
    public ResponseEntity<String> addFilm(final Authentication auth, @RequestParam(value = "title") final String title,
                                          @RequestParam(value = "date") final String date) throws ParseException {

        return ResponseEntity.status(HttpStatus.CREATED).body(filmService.addFilm(auth, title, date));
    }

    @DeleteMapping("/deleteFilm/{title}")
    public ResponseEntity<String> deleteFilm (@PathVariable("title") final String title, final Authentication auth) {
        logger.info("Trying to delete film...");
        return ResponseEntity.status(HttpStatus.OK).body(filmService.deleteFilm(title, auth));

    }
}
