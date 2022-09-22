package com.silentium.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VoucherClientId implements Serializable {

    private static final long serialVersionUID = -1879496099880600774L;

    @Column(name = "voucher_id", nullable = false)
    private Integer voucherId;

    @Column(name = "person_id", nullable = false)
    private Integer personId;

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VoucherClientId entity = (VoucherClientId) o;
        return Objects.equals(this.voucherId, entity.voucherId) &&
                Objects.equals(this.personId, entity.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voucherId, personId);
    }

}