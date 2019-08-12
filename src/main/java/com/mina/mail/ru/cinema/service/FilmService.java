package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.converter.FilmDateConverter;
import com.mina.mail.ru.cinema.entity.FilmDateEntity;
import com.mina.mail.ru.cinema.entity.FilmEntity;
import com.mina.mail.ru.cinema.entity.FilmTicketEntity;
import com.mina.mail.ru.cinema.dto.FilmDateDto;
import com.mina.mail.ru.cinema.dto.FilmTicketDto;
import com.mina.mail.ru.cinema.repository.FilmRepository;
import com.mina.mail.ru.cinema.converter.FilmConverter;
import com.mina.mail.ru.cinema.converter.FilmTicketConverter;
import com.mina.mail.ru.cinema.dto.FilmDto;
import com.mina.mail.ru.cinema.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
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

    private final FilmRepository filmRepository;
    private final FilmConverter filmConverter;
    private final UserRepository userRepository;
    private final FilmDateConverter filmDateConverter;
    private final FilmTicketConverter ticketConverter;

    public FilmService(final FilmRepository filmRepository, final FilmConverter filmConverter, final UserRepository userRepository, final FilmDateConverter filmDateConverter, final FilmTicketConverter ticketConverter) {
        this.filmRepository = filmRepository;
        this.filmConverter = filmConverter;
        this.userRepository = userRepository;
        this.filmDateConverter = filmDateConverter;
        this.ticketConverter = ticketConverter;
    }

    public List<FilmDto> getFilms() {
        final List<FilmDto> filmsDto = new ArrayList<>();
        final List<FilmEntity> filmEntities = filmRepository.getFilms();
        logger.info("Unique films were received...");
        for (FilmEntity filmEntity : filmEntities) {
           final FilmDto filmDto = filmConverter.convertToDto(filmEntity);
           filmsDto.add(filmDto);
        }
        return filmsDto;
    }

    public FilmDto getFilmByTitle(final String film) {
        final FilmEntity filmEntity = filmRepository.getFilmByTitle(film);
        logger.info("Film " + film + " was received...");
        List<FilmDateEntity> filmDates = filmEntity.getDates();
        final List<FilmDateDto> filmDateDtos = new ArrayList<>();
        for (FilmDateEntity f : filmDates) {
            filmDateDtos.add(filmDateConverter.convertToDto(f));
        }

        final FilmDto filmDto = filmConverter.convertToDto(filmEntity);
        filmDto.setDates(formatDates(filmDateDtos));

        return filmDto;
    }

    public FilmDateDto getTicketsByDate(final Integer dateId) {
        final FilmDateEntity dateEntity = filmRepository.getTicketsByDate(dateId);
        final List<FilmTicketDto> ticketDtos = new ArrayList<>();

        for (FilmTicketEntity t : dateEntity.getTickets()) {
            ticketDtos.add(ticketConverter.convertToDto(t));
        }
        logger.info("Film tickets were received...");
        final FilmDateDto filmDateDto = filmDateConverter.convertToDto(dateEntity);
        filmDateDto.setTickets(ticketDtos);
        return filmDateDto;
    }

    public String addFilm (final Authentication auth, final FilmDto filmDto) throws ParseException {
        final String title = filmDto.getTitle();
        final String date = filmDto.getFormattedDate();
        if (!filmDto.getFormattedDate().replaceAll("(\\d){2}(-){1}(\\d){2}(-){1}(\\d){4}", "isOk").equals("isOk")) {
            return "Wrong date pattern, use dd-mm-yyyy";
        }
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        final String dateAndTime = date +  " 12:00:00";
        LocalDateTime filmDateAndTime = LocalDateTime.parse(dateAndTime, formatter);
        final FilmEntity filmEntity = filmRepository.getFilmByTitle(title);
        final FilmDateEntity dateEntity = new FilmDateEntity();
        dateEntity.setDateAndTime(filmDateAndTime);
        if (checkPermission(auth)) {
            return "Not enough permissions for this action";
        }
        else if (filmEntity != null) {
            List<FilmDateEntity> dates = filmEntity.getDates();
            for (FilmDateEntity filmDatesEntity : dates) {
                if (formatter.format(filmDatesEntity.getDateAndTime()).equals(dateAndTime)) {
                    return "Film with date of performance - " + dateAndTime + " already exists!";
                }
            }
            dates.add(dateEntity);
            filmEntity.setDates(dates);
            filmRepository.save(filmEntity);
            return "Date of performance - " + dateAndTime + " for film - " + title + " was successfully added!";
        }
        else {
            final FilmEntity newFilm = new FilmEntity();
            newFilm.setTitle(title);
            final List<FilmDateEntity> dates = new ArrayList<>();
            dates.add(dateEntity);
            newFilm.setDates(dates);
            filmRepository.save(newFilm);
            return "Film - " + title + " with date of performance - " + dateAndTime + " were successfully added!";
        }
    }

    public List<FilmDateDto> formatDates(final List<FilmDateDto> list) {

        Collections.sort(list, new Comparator<FilmDateDto>() {
            @Override
            public int compare(final FilmDateDto o1, final FilmDateDto o2) {
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

    public String deleteFilm(final String title, final Authentication auth) {
        if (checkPermission(auth)) {
            return "Not enough permissions for this action";
        }
        else {
            final FilmEntity filmEntity = filmRepository.getFilmByTitle(title);
            if (filmEntity != null) {
                filmRepository.deleteById(filmEntity.getId());
                logger.info("Film was deleted.");
                return "Film was deleted.";
            }
            else {
                return "Film was not found";
            }
        }
    }

    public String deleteDate (final Integer id, final Authentication auth) {
        if (checkPermission(auth)) {
            return "Not enough permissions for this action";
        }
        else {
            filmRepository.deleteDate(id);
            logger.info("Date was deleted.");
            return "Date was deleted.";
        }
    }

    public boolean checkPermission (final Authentication auth) {
        if (!userRepository.getUserByName(auth.getName()).getRole().equals("ADMIN")) {
            return true;
        }
        else return false;
    }


}
