package com.silentium.dto;

import com.silentium.model.*;

import java.util.Date;

public class TourDto {

    private int id;
    private String name;
    private Date date_arrival;
    private Date date_departure;
    private String description;
    private int quantity_night;
    private float price;
    private TourOperator tour_operator;
    private TypeFood type_food;
    private TypeTour type_tour;
    private TypeTransport type_transport;
    private TourStructure tour_structure;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate_arrival() {
        return date_arrival;
    }

    public void setDate_arrival(Date date_arrival) {
        this.date_arrival = date_arrival;
    }

    public Date getDate_departure() {
        return date_departure;
    }

    public void setDate_departure(Date date_departure) {
        this.date_departure = date_departure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity_night() {
        return quantity_night;
    }

    public void setQuantity_night(int quantity_night) {
        this.quantity_night = quantity_night;
    }

    public TourOperator getTour_operator() {
        return tour_operator;
    }

    public void setTour_operator(TourOperator tour_operator) {
        this.tour_operator = tour_operator;
    }

    public TypeFood getType_food() {
        return type_food;
    }

    public void setType_food(TypeFood type_food) {
        this.type_food = type_food;
    }

    public TypeTour getType_tour() {
        return type_tour;
    }

    public void setType_tour(TypeTour type_tour) {
        this.type_tour = type_tour;
    }

    public TypeTransport getType_transport() {
        return type_transport;
    }

    public void setType_transport(TypeTransport type_transport) {
        this.type_transport = type_transport;
    }

    public TourStructure getTour_structure() {
        return tour_structure;
    }

    public void setTour_structure(TourStructure tour_structure) {
        this.tour_structure = tour_structure;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
