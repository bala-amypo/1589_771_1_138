package com.example.demo.model;

import java.security.Timestamp;
@Entity
public class DigitalKey {
    private Long id;
    private RoomBooking booking;
    private String keyValue;
    private Timestamp issuedAt;
    private Timestamp expiresAt;
    private Boolean active;
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
     
    
}
