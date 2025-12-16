package com.example.demo.model;

import java.security.Timestamp;
public class Guest {
    private long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Boolean verified;
    private Boolean active;
    private String role;
    private Timestamp createdAt;
    public Guest(String fullName, String email, String phoneNumber, Boolean verified, Boolean active, String role,
            Timestamp createdAt) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.verified = verified;
        this.active = active;
        this.role = role;
        this.createdAt = createdAt;
    }
    public long getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Boolean getVerified() {
        return verified;
    }
    public Boolean getActive() {
        return active;
    }
    public String getRole() {
        return role;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }



    
}
