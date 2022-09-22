package com.silentium.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExcursionStructureId implements Serializable {
    private static final long serialVersionUID = 3796816601831931957L;
    @Column(name = "excursion_id", nullable = false)
    private Integer excursionId;

    @Column(name = "tour_structure_id", nullable = false)
    private Integer tourStructureId;

    public Integer getExcursionId() {
        return excursionId;
    }

    public void setExcursionId(Integer excursionId) {
        this.excursionId = excursionId;
    }

    public Integer getTourStructureId() {
        return tourStructureId;
    }

    public void setTourStructureId(Integer tourStructureId) {
        this.tourStructureId = tourStructureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ExcursionStructureId entity = (ExcursionStructureId) o;
        return Objects.equals(this.tourStructureId, entity.tourStructureId) &&
                Objects.equals(this.excursionId, entity.excursionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourStructureId, excursionId);
    }

}