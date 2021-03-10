package com.movie.ticketing.system.movieticketingsystem.entities;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    private String name;
    private String mobile;
    private String email;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long id;

    public User(String name, String mobile, String email) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;

    }
    User(){}

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
