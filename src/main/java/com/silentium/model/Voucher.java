package com.silentium.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id", nullable = false)
    private int id;

    @NotNull(message="Amount is empty")
    @Column(name = "amount", nullable=false)
    private float amount;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "voucher_date", nullable = false)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    private Date voucherDate;

    @Column(name = "voucher_update_date", nullable = false)
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime voucherUpdateDate;

    @Column(name = "place_issue", nullable = false, length = 75)
    private String placeIssue;

    @Column(name = "comment")
    private String comment;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_status_voucher_id", nullable = false)
    private StatusVoucher statusVoucher;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_tour_date_id", nullable = false)
    private TourDate tourDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_manager_id", nullable = false)
    private Person manager;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_payment_id", nullable = false)
    private TypePayment payment;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "voucher_client",
            joinColumns = { @JoinColumn(name = "voucher_id") },
            inverseJoinColumns = { @JoinColumn(name = "person_id") })
    private Set<Person> persons = new HashSet<Person>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(Date voucherDate) {
        this.voucherDate = voucherDate;
    }

    public LocalDateTime getVoucherUpdateDate() {
        return voucherUpdateDate;
    }

    public void setVoucherUpdateDate(LocalDateTime voucherUpdateDate) {
        this.voucherUpdateDate = voucherUpdateDate;
    }

    public String getPlace_issue() {
        return placeIssue;
    }

    public void setPlace_iIssue(String placeIssue) {
        this.placeIssue = placeIssue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public StatusVoucher getStatusVoucher() {
        return statusVoucher;
    }

    public void setStatusVoucher(StatusVoucher statusVoucher) {
        this.statusVoucher = statusVoucher;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public TypePayment getPayment() {
        return payment;
    }

    public void setPayment(TypePayment payment) {
        this.payment = payment;
    }

    public TourDate getTourDate() {
        return tourDate;
    }

    public void setTourDate(TourDate tourDate) {
        this.tourDate = tourDate;
    }

    public String getPlaceIssue() {
        return placeIssue;
    }

    public void setPlaceIssue(String placeIssue) {
        this.placeIssue = placeIssue;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}

