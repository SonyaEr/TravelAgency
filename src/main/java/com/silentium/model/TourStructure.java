package com.silentium.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tour_structure")
public class TourStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_structure_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_city_id", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_hotel_id")
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_tour_id", nullable = false)
    private Tour tour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        if (description == null)
            return "";
        else
            return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public String getDurationDay(){
        int min = duration;
        float hour = min / 60;
        double d = Math.floor(hour / 24);
        double h = Math.floor(hour % 24);
        double m = Math.floor(min % 60);
        String day = "";
        if (d!=0) {
           day += String.format("%.0f",d) + " д. ";
        }
        if (h!=0) {
            day += String.format("%.0f",h) + " ч. ";
        }
        if (m!=0) {
            day += String.format("%.0f",m) + " м. ";
        }

        return day;
    }
}