package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.dbo.FilmEntity;
import com.mina.mail.ru.cinema.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.repository.FilmDAO;
import com.mina.mail.ru.cinema.converter.FilmConverter;
import com.mina.mail.ru.cinema.converter.FilmTicketConverter;
import com.mina.mail.ru.cinema.dto.FilmDto;
import com.mina.mail.ru.cinema.dto.FilmTicketDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mina on 29.04.2019.
 */

@Service
public class FilmService {

    private static final Logger logger = LoggerFactory.getLogger(FilmService.class);

    private FilmDAO filmDAO;
    private FilmConverter filmConverter;
    private FilmTicketConverter filmTicketConverter;

    @Autowired
    public FilmService(FilmDAO filmDAO, FilmConverter converter, FilmTicketConverter filmTicketConverter) {
        this.filmDAO = filmDAO;
        this.filmConverter = converter;
        this.filmTicketConverter = filmTicketConverter;
    }

    public List<FilmDto> getFilms() {
        List<FilmDto> filmsDto = new ArrayList<>();
        List<FilmEntity> filmsDbo = filmDAO.getFilms();
        logger.info("Unique films were received...");
        for (FilmEntity d : filmsDbo) {
            filmsDto.add(filmConverter.convertToDto(d));
        }
        return filmsDto;
    }

    public List<FilmDto> getDatesByFilm(String film) {
        List<FilmDto> dates = new ArrayList<>();
        List<FilmEntity> datesDbo = filmDAO.geDatesByFilm(film);
        logger.info("Film with dates was received...");
        for (FilmEntity d : datesDbo) {
            Set<FilmTicketDto> ticketDtos = new HashSet<>();
            for (FilmTicketEntity ticketDbo : d.getTickets()) {
               ticketDtos.add(filmTicketConverter.convertToDto(ticketDbo));
            }
            FilmDto filmDto = filmConverter.convertToDto(d);
            filmDto.setTickets(ticketDtos);
            dates.add(filmDto);
        }
        return dates;
    }
}
