package com.org.ecommerce.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(unique = true)
    private String username;
    private String hasedPassword;
    private String salt;
    @Column(unique = true)
    private String email;

    public Admin(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore // to ignore the password while sending the response
    public String getHasedPassword() {
        return hasedPassword;
    }

    public void setHasedPassword(String hasedPassword) {
        this.hasedPassword = hasedPassword;
    }

    @JsonIgnore // to ignore the salt while sending the response
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }
}
