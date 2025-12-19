package com.example.demo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;

@Entity
@Table(name = "digital_keys")
public class DigitalKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    @JsonIgnoreProperties({"roommates", "guest"}) // Simplifies the JSON response
    private RoomBooking booking;

    @Column(nullable = false, unique = true)
    private String keyValue;

    @Column(nullable = false)
    private Timestamp issuedAt;

    @Column(nullable = false)
    private Timestamp expiresAt;

    @Column(nullable = false)
    private Boolean active = true;

    public DigitalKey() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public RoomBooking getBooking() { return booking; }
    public void setBooking(RoomBooking booking) { this.booking = booking; }

    public String getKeyValue() { return keyValue; }
    public void setKeyValue(String keyValue) { this.keyValue = keyValue; }

    public Timestamp getIssuedAt() { return issuedAt; }
    public void setIssuedAt(Timestamp issuedAt) { this.issuedAt = issuedAt; }

    public Timestamp getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Timestamp expiresAt) { this.expiresAt = expiresAt; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}