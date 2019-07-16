package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.dbo.FilmDatesEntity;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        List<FilmEntity> filmEntities = filmDAO.getFilms();

        logger.info("Unique films were received...");
        for (FilmEntity filmEntity : filmEntities) {
            List<String> formattedDates = new ArrayList<>();
            List<FilmDatesEntity> filmDates = filmEntity.getDates();

            Collections.sort(filmDates, new Comparator<FilmDatesEntity>() {
                @Override
                public int compare(FilmDatesEntity o1, FilmDatesEntity o2) {
                    if (o1 == null || o2 == null) {
                        return 0;
                    }
                    else return o1.getDateAndTime().compareTo(o2.getDateAndTime());
                }
            });

            for (FilmDatesEntity dates : filmDates) {
                formattedDates.add(DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss").format(dates.getDateAndTime()));
            }
            FilmDto filmDto = filmConverter.convertToDto(filmEntity);

            filmDto.setFormattedDates(formattedDates);
            filmsDto.add(filmDto);

        }
        return filmsDto;
    }

    public List<FilmDto> getFilmsByTitle(String film) {
        List<FilmDto> films = new ArrayList<>();
        List<FilmEntity> filmEntities = filmDAO.getFilmsByTitle(film);
        logger.info("Film with dates was received...");
        for (FilmEntity d : filmEntities) {
            Set<FilmTicketDto> ticketDtos = new HashSet<>();
            for (FilmTicketEntity ticketDbo : d.getTickets()) {
               ticketDtos.add(filmTicketConverter.convertToDto(ticketDbo));
            }
            FilmDto filmDto = filmConverter.convertToDto(d);
            filmDto.setTickets(ticketDtos);
            films.add(filmDto);
        }
        return films;
    }

    public FilmDto getFilmTickets(String title, Integer date) {
        FilmEntity film = filmDAO.getFilmTickets(title);
        logger.info("Film tickets were received...");
        FilmDto filmDto = filmConverter.convertToDto(film);
        return filmDto;
    }

    public String addFilm (String title, String filmdate) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateAndTime = filmdate +  " 12:00:00";
        LocalDateTime filmdateAndTime = LocalDateTime.parse(dateAndTime, formatter);
        FilmEntity filmEntity = filmDAO.getFilmByTitle(title);
        FilmDatesEntity date = new FilmDatesEntity();
        date.setDateAndTime(filmdateAndTime);
        if (filmEntity != null) {
            List<FilmDatesEntity> dates = filmEntity.getDates();
            for (FilmDatesEntity filmDatesEntity : dates) {
                System.out.println("DDD " + filmDatesEntity.getDateAndTime());
                if (formatter.format(filmDatesEntity.getDateAndTime()).equals(dateAndTime)) {
                    return "Film with date of performance - " + dateAndTime + " already exists!";
                }
            }
            dates.add(date);
            filmEntity.setDates(dates);
            filmDAO.save(filmEntity);
            return "Date of performance - " + dateAndTime + " for film - " + title + " was successfully added!";
        }
        else {
            FilmEntity newFilm = new FilmEntity();
            newFilm.setTitle(title);
            List<FilmDatesEntity> dates = new ArrayList<>();
            dates.add(date);
            newFilm.setDates(dates);
            filmDAO.save(newFilm);
            return "Film - " + title + " with date of performance - " + dateAndTime + " were successfully added!";
        }
    }

}
