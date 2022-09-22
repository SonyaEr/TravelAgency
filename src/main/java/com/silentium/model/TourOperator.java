package com.silentium.model;

import javax.persistence.*;

@Entity
@Table(name = "tour_operator")
public class TourOperator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_operator_id", nullable = false)
    private Integer id;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_name", nullable = false, length = 40)
    private String contactName;

    @Column(name = "contact_telnum", nullable = false, length = 40)
    private String contactTelnum;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTelnum() {
        return contactTelnum;
    }

    public void setContactTelnum(String contactTelnum) {
        this.contactTelnum = contactTelnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}