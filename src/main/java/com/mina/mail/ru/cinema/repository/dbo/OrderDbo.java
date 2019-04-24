package com.mina.mail.ru.cinema.repository.dbo;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Mina on 22.04.2019.
 */

@Entity
@Table(name = "order")
public class OrderDbo {

    @Id
    @Column(name = "id")
    private Integer id;

    private Integer seatNumber;

    private Integer visitorId;

    public OrderDbo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
    }
}
