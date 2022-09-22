package com.silentium.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private int id;

    @Column(name = "active", nullable = false)
    private int active;

    @Column(name = "login", nullable = false)
    @Email(message = "Please provide a valid Login")
    @NotEmpty(message = "Please provide an login")
    private String login;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Please provide your password")
   private String password;

    @Column(name = "date_joined", nullable = false)
    private Date dateJoined;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_person_id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_role_id", nullable = false)
    private Role role;

    public Account(){};


    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



}