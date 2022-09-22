package com.silentium.model;

import javax.persistence.*;

@Entity
@Table(name = "excursion_structure")
public class ExcursionStructure {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "excursion_id", nullable = false)
    private Excursion excursion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_structure_id", nullable = false)
    private TourStructure tourStructure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    public TourStructure getTourStructure() {
        return tourStructure;
    }

    public void setTourStructure(TourStructure tourStructure) {
        this.tourStructure = tourStructure;
    }

}