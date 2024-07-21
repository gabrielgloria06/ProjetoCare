package com.example.myapplication.models.user;

import java.util.Date;

public class CadastroUser {

    private Long id;

    private String name;

    private String password;

    private String email;

    private String birthdate;

    public CadastroUser() {
    }

    public CadastroUser(String name, String password, String email, String birthdate) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}