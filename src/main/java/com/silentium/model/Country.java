package com.silentium.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", nullable = false)
    private int id;

    @NotEmpty(message="Name is empty")
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @OneToMany(mappedBy="country", cascade = CascadeType.ALL)
    protected List<City> cities;

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

}