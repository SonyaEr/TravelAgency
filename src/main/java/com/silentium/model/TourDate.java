package com.silentium.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tour_date")
public class TourDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_date_id", nullable = false)
    private int id;

    @Column(name = "date_arrival", nullable = false)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateArrival;

    @NotNull(message="Price is empty")
    @Column(name = "price", nullable=false)
    private float price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_tour_id", nullable = false)
    private Tour tour;

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDateDeparture(){
        LocalDate ld_dateArrival = new java.sql.Date(dateArrival.getTime()).toLocalDate();
        LocalDate ld_dateADeparture = ld_dateArrival.plusDays(tour.getQuantityNight());
        Date dateDeparture = java.sql.Date.valueOf(ld_dateADeparture);
        return dateDeparture;
    }
}