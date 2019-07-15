package com.mina.mail.ru.cinema.dbo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mina on 14.07.2019.
 */

@Entity
@Table(name = "filmdate")
public class FilmDatesEntity implements Serializable {

   @Id
   @GeneratedValue(strategy = IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "filmdate", columnDefinition = "DATETIME")
   private LocalDateTime dateAndTime;

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
}
