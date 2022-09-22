package com.silentium.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "`order`")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private int id;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "order_date", nullable = false)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(name = "note", length = 100)
    private String note;

    @Column(name = "comment")
    private String comment;

    @NotNull(message="Priority is empty")
    @Column(name = "priority", nullable=false)
    private float priority;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_client_id", nullable = false)
    @JsonIgnoreProperties(value = {"order", "hibernateLazyInitializer"})
    private Person client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_manager_id", nullable = false)
    @JsonIgnoreProperties(value = {"order", "hibernateLazyInitializer"})
    private Person manager;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_status_order_id", nullable = false)
    @JsonIgnoreProperties(value = {"order", "hibernateLazyInitializer"})
    private StatusOrder statusOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_tour_date_id", nullable = false)
    @JsonIgnoreProperties(value = {"order", "hibernateLazyInitializer"})
    private TourDate tourDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getPriority() {
        return priority;
    }

    public void setPriority(float priority) {
        this.priority = priority;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public TourDate getTourDate() { return tourDate;}

    public void setTourDate(TourDate tourDate) {
        this.tourDate = tourDate;
    }



}