package com.mina.mail.ru.cinema.service;

import com.mina.mail.ru.cinema.converter.FilmDateConverter;
import com.mina.mail.ru.cinema.dbo.FilmDateEntity;
import com.mina.mail.ru.cinema.dbo.FilmEntity;
import com.mina.mail.ru.cinema.dbo.FilmTicketEntity;
import com.mina.mail.ru.cinema.dto.FilmDateDto;
import com.mina.mail.ru.cinema.dto.FilmTicketDto;
import com.mina.mail.ru.cinema.dto.UserDto;
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

    private FilmRepository filmRepository;
    private FilmConverter filmConverter;
    private UserRepository userRepository;
    private FilmDateConverter filmDateConverter;
    private FilmTicketConverter ticketConverter;

    public FilmService(FilmRepository filmRepository, FilmConverter filmConverter, UserRepository userRepository, FilmDateConverter filmDateConverter, FilmTicketConverter ticketConverter) {
        this.filmRepository = filmRepository;
        this.filmConverter = filmConverter;
        this.userRepository = userRepository;
        this.filmDateConverter = filmDateConverter;
        this.ticketConverter = ticketConverter;
    }

    public List<FilmDto> getFilms() {
        List<FilmDto> filmsDto = new ArrayList<>();
        List<FilmEntity> filmEntities = filmRepository.getFilms();
        logger.info("Unique films were received...");
        for (FilmEntity filmEntity : filmEntities) {
           final FilmDto filmDto = filmConverter.convertToDto(filmEntity);
           filmsDto.add(filmDto);
        }
        return filmsDto;
    }

    public FilmDto getFilmByTitle(String film) {
        FilmEntity filmEntity = filmRepository.getFilmByTitle(film);
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

    public FilmDateDto getTicketsByDate(Integer dateId) {
        FilmDateEntity dateEntity = filmRepository.getTicketsByDate(dateId);
        final List<FilmTicketDto> ticketDtos = new ArrayList<>();

        for (FilmTicketEntity t : dateEntity.getTickets()) {
            ticketDtos.add(ticketConverter.convertToDto(t));
        }
        logger.info("Film tickets were received...");
        final FilmDateDto filmDateDto = filmDateConverter.convertToDto(dateEntity);
        filmDateDto.setTickets(ticketDtos);
        return filmDateDto;
    }

    public String addFilm (Authentication auth, String title, String filmdate) throws ParseException {
        if (!filmdate.replaceAll("(\\d){2}(-){1}(\\d){2}(-){1}(\\d){4}", "isOk").equals("isOk")) {
            return "Wrong date pattern, use dd-mm-yyyy";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateAndTime = filmdate +  " 12:00:00";
        LocalDateTime filmdateAndTime = LocalDateTime.parse(dateAndTime, formatter);
        FilmEntity filmEntity = filmRepository.getFilmByTitle(title);
        FilmDateEntity date = new FilmDateEntity();
        date.setDateAndTime(filmdateAndTime);
        if (!userRepository.getUserByName(auth.getName()).getRole().equals("ADMIN")) {
            return "Not enough permissions for this action";
        }
        else if (filmEntity != null) {
            List<FilmDateEntity> dates = filmEntity.getDates();
            for (FilmDateEntity filmDatesEntity : dates) {
                if (formatter.format(filmDatesEntity.getDateAndTime()).equals(dateAndTime)) {
                    return "Film with date of performance - " + dateAndTime + " already exists!";
                }
            }
            dates.add(date);
            filmEntity.setDates(dates);
            filmRepository.save(filmEntity);
            return "Date of performance - " + dateAndTime + " for film - " + title + " was successfully added!";
        }
        else {
            FilmEntity newFilm = new FilmEntity();
            newFilm.setTitle(title);
            List<FilmDateEntity> dates = new ArrayList<>();
            dates.add(date);
            newFilm.setDates(dates);
            filmRepository.save(newFilm);
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

    public String deleteFilm(String title, Authentication auth) {
        if (!userRepository.getUserByName(auth.getName()).getRole().equals("ADMIN")) {
            return "Not enough permissions for this action";
        }
        else {
            FilmEntity filmEntity = filmRepository.getFilmByTitle(title);
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


}
