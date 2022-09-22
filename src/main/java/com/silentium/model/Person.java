package com.silentium.model;

import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 40)
    @NotEmpty(message = "Please provide your name")
    private String name;

    @Column(name = "surname", length = 40)
    @NotEmpty(message = "Please provide your surname")
    private String surname;

    @Column(name = "patronymic", length = 40)
    private String patronymic;

    @Column(name = "email")
    private String email;

    @Column(name = "tel_num", nullable = false, length = 40)
    private String telNum;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "document_type", nullable = false, length = 40)
    private String documentType;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @ManyToMany(mappedBy = "persons")
    private Set<Voucher> vouchers = new HashSet<Voucher>();

    public Person() {
    }

    public Person(@NotEmpty(message = "Name is empty") String name,
                  String surname,
                  @Email(message = "*Please provide a valid Email") @NotEmpty(message = "Email is empty") String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFIO(){
        return surname+" "+name+" "+patronymic;
    }

    public Set<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(Set<Voucher> vouchers) {
        this.vouchers = vouchers;
    }
}