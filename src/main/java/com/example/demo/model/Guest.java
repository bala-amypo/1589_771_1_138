package com.example.demo.model;

import jakarta.persistence.*; // Fixes @Entity, @Id, etc.
import java.time.LocalDateTime; // Fixes LocalDateTime error

@Entity
public class Guest { ... }
@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;
    private Boolean verified;
    private Boolean active = true;
    private String role;
    private LocalDateTime createdAt;
    public Guest(String fullName, String email, String phoneNumber, Boolean verified, Boolean active, String role) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.verified = verified;
        this.active = active;
        this.role = role;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        if(this.active == null){
            this.active = true;
        }
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
   



    
}
