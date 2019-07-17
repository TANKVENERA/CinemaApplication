package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.converter.FilmDateConverter;
import com.mina.mail.ru.cinema.dbo.FilmDateEntity;
import com.mina.mail.ru.cinema.dbo.FilmEntity;
import com.mina.mail.ru.cinema.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.dto.FilmDateDto;
import com.mina.mail.ru.cinema.repository.FilmDAO;
import com.mina.mail.ru.cinema.converter.FilmConverter;
import com.mina.mail.ru.cinema.converter.FilmTicketConverter;
import com.mina.mail.ru.cinema.dto.FilmDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
    private FilmDateConverter filmDateConverter;

    @Autowired
    public FilmService(FilmDAO filmDAO, FilmConverter converter, FilmTicketConverter filmTicketConverter, FilmDateConverter filmDateConverter) {
        this.filmDAO = filmDAO;
        this.filmConverter = converter;
        this.filmTicketConverter = filmTicketConverter;
        this.filmDateConverter = filmDateConverter;
    }

    public List<FilmDto> getFilms() {
        List<FilmDto> filmsDto = new ArrayList<>();
        List<FilmEntity> filmEntities = filmDAO.getFilms();
        logger.info("Unique films were received...");
        for (FilmEntity filmEntity : filmEntities) {
            FilmDto filmDto = filmConverter.convertToDto(filmEntity);
            filmsDto.add(filmDto);
        }
        return filmsDto;
    }

    public FilmDto getFilmByTitle(String film) {
        FilmEntity filmEntity = filmDAO.getFilmByTitle(film);
        logger.info("Film " + film + " was received...");
        List<FilmDateEntity> filmDates = filmEntity.getDates();
        List<FilmDateDto> filmDateDtos = new ArrayList<>();
        for (FilmDateEntity f : filmDates) {
            filmDateDtos.add(filmDateConverter.convertToDto(f));
        }

        FilmDto filmDto = filmConverter.convertToDto(filmEntity);
        filmDto.setDates(formatDates(filmDateDtos));

        return filmDto;
    }

    public FilmDto getFilmTickets(String title, String date) {
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
        FilmDateEntity date = new FilmDateEntity();
        date.setDateAndTime(filmdateAndTime);
        if (filmEntity != null) {
            List<FilmDateEntity> dates = filmEntity.getDates();
            for (FilmDateEntity filmDatesEntity : dates) {
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
            List<FilmDateEntity> dates = new ArrayList<>();
            dates.add(date);
            newFilm.setDates(dates);
            filmDAO.save(newFilm);
            return "Film - " + title + " with date of performance - " + dateAndTime + " were successfully added!";
        }
    }

    public List<FilmDateDto> formatDates(List<FilmDateDto> list) {

        Collections.sort(list, new Comparator<FilmDateDto>() {
            @Override
            public int compare(FilmDateDto o1, FilmDateDto o2) {
                if (o1 == null || o2 == null) {
                    return 0;
                }
                else return o1.getDateAndTime().compareTo(o2.getDateAndTime());
            }
        });

        for (FilmDateDto d : list) {
            d.setFormattedDate(DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss").format(d.getDateAndTime()));
        }
        return list;
    }

    public void deleteFilm(String title) {
        FilmEntity filmEntity = filmDAO.getFilmByTitle(title);
        if (filmEntity != null) {
            filmDAO.deleteById(filmEntity.getId());
            logger.info("Film was deleted.");
        }

    }

}
