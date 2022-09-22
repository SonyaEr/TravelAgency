package com.silentium.dto;

import com.silentium.model.Person;
import com.silentium.model.StatusOrder;
import com.silentium.model.Tour;

import javax.persistence.Column;
import java.util.Date;

public class OrderDto {

    private int id;
    private int amount;
    @Column(name = "note", length = 100)
    private String note;
    @Column(name = "comment")
    private String comment;
    private Date order_date;
    private Person client;
    private StatusOrder status_order;
    private Tour tour;
    private int tour_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public StatusOrder getStatus_order() {
        return status_order;
    }

    public void setStatus_order(StatusOrder status_order) {
        this.status_order = status_order;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getTour_id() {
        return tour_id;
    }

    public void setTour_id(int tour_id) {
        this.tour_id = tour_id;
    }

    public Tour getTourById(int id) {
        return tour;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
