package com.example.demo.model;

import java.sql.LocalDateTime;
import jakarta.persistence.*;
@Entity
public class DigitalKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private RoomBooking booking;
    @Column(unique = true)
    private String keyValue;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    private Boolean active = true;
    public DigitalKey(RoomBooking booking, String keyValue, Timestamp issuedAt, Timestamp expiresAt, Boolean active) {
        this.booking = booking;
        this.keyValue = keyValue;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.active = active;
    }
    public Long getId() {
        return id;
    }
    public RoomBooking getBooking() {
        return booking;
    }
    public String getKeyValue() {
        return keyValue;
    }
    public Timestamp getIssuedAt() {
        return issuedAt;
    }
    public Timestamp getExpiresAt() {
        return expiresAt;
    }
    public Boolean getActive() {
        return active;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setBooking(RoomBooking booking) {
        this.booking = booking;
    }
    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
    public void setIssuedAt(Timestamp issuedAt) {
        this.issuedAt = issuedAt;
    }
    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
     
    @PrePersist
    @PreUpdate
    private void validateDates(){
        if(expiresAt.isBefore(issuedAt)){
            throw new IllegalStateException("Expiration data must be after or equal to the issued date.")
        }
    }
}
