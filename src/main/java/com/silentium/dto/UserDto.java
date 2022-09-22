package com.silentium.dto;

import com.silentium.model.Account;
import com.silentium.model.Person;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserDto {

    private int id;
    private int active;
    @NotEmpty(message = "Please provide an login")
    private String login;
    @NotEmpty(message = "Please provide an password")
    private String password;
    @NotEmpty(message = "Please provide an name")
    private String name;
    @NotEmpty(message = "Please provide an surname")
    private String surname;
    private Person person;
    private Account account;
    private int person_id;
    private int account_id;

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Person getClient() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public Person getPersonById(int id) {
        return person;
    }



    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Account getAccountById(int id) {
        return account;
    }


}
