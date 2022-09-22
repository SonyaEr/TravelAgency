package com.silentium.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tour")
public class Tour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id", nullable = false)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "quantity_night", nullable = false)
    private Integer quantityNight;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_tour_operator_id", nullable = false)
    @JsonIgnoreProperties(value = {"tour", "hibernateLazyInitializer"})
    private TourOperator tourOperator;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_type_food_id", nullable = false)
    @JsonIgnoreProperties(value = {"tour", "hibernateLazyInitializer"})
    private TypeFood typeFood;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_type_tour_id", nullable = false)
    @JsonIgnoreProperties(value = {"tour", "hibernateLazyInitializer"})
    private TypeTour typeTour;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_type_transport_id", nullable = false)
    @JsonIgnoreProperties(value = {"tour", "hibernateLazyInitializer"})
    private TypeTransport typeTransport;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantityNight() {
        return quantityNight;
    }

    public void setQuantityNight(Integer quantityNight) {
        this.quantityNight = quantityNight;
    }

    public TourOperator getTourOperator() {
        return tourOperator;
    }

    public void setTourOperator(TourOperator tourOperator) {
        this.tourOperator = tourOperator;
    }

    public TypeFood getTypeFood() {
        return typeFood;
    }

    public void setTypeFood(TypeFood typeFood) {
        this.typeFood = typeFood;
    }

    public TypeTour getTypeTour() {
        return typeTour;
    }

    public void setTypeTour(TypeTour typeTour) {
        this.typeTour = typeTour;
    }

    public TypeTransport getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(TypeTransport typeTransport) {
        this.typeTransport = typeTransport;
    }
}