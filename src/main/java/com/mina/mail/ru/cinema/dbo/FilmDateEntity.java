package com.mina.mail.ru.cinema.dbo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Mina on 14.07.2019.
 */

@Entity
@Data
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

}
