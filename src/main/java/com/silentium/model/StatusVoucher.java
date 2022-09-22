package com.silentium.model;

import javax.persistence.*;

@Entity
@Table(name = "status_voucher")
public class StatusVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_voucher_id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

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

}