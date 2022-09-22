package com.silentium.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id", nullable = false)
    private int id;

    @NotEmpty(message="Name is empty")
    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @NotEmpty(message="Аddress is empty")
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description")
    private String description;

    @NotEmpty(message="Stars is empty")
    @Column(name = "stars", nullable = false)
    private int stars;

    @NotEmpty(message="Telephon number is empty")
    @Column(name = "tel_num", nullable = false, length = 40)
    private String telNum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_city_id", nullable = false)
    private City city;

    public int getId() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public Country getCountry() {
        if (city!=null)
            return city.getCountry();
        else
            return null;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}