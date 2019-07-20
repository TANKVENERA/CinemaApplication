package com.mina.mail.ru.cinema.dbo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mina on 14.07.2019.
 */

@Entity
@Table(name = "filmdate")
public class FilmDateEntity implements Serializable {

   @Id
   @GeneratedValue(strategy = IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "filmdate", columnDefinition = "DATETIME")
   private LocalDateTime dateAndTime;

   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name = "filmdate_id")
   private List<FilmTicketEntity> tickets;

   public LocalDateTime getDateAndTime() {
      return dateAndTime;
   }

   public void setDateAndTime(LocalDateTime dateAndTime) {
      this.dateAndTime = dateAndTime;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public List<FilmTicketEntity> getTickets() {
      return tickets;
   }

   public void setTickets(List<FilmTicketEntity> tickets) {
      this.tickets = tickets;
   }
}
