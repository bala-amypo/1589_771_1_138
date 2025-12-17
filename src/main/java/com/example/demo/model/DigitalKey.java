package com.example.demo.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class DigitalKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fixed: Added Relationship Annotation
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private RoomBooking booking;

    @Column(unique = true)
    private String keyValue;

    // Standardized to LocalDateTime
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    private Boolean active = true;

    // 1. Fixed: Added Required No-Args Constructor
    public DigitalKey() {}

    // 2. Fixed: Constructor types changed from Timestamp to LocalDateTime
    public DigitalKey(RoomBooking booking, String keyValue, LocalDateTime issuedAt, LocalDateTime expiresAt, Boolean active) {
        this.booking = booking;
        this.keyValue = keyValue;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.active = active;
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public RoomBooking getBooking() { return booking; }
    public void setBooking(RoomBooking booking) { this.booking = booking; }

    public String getKeyValue() { return keyValue; }
    public void setKeyValue(String keyValue) { this.keyValue = keyValue; }

    // 3. Fixed: Getter/Setter types changed to LocalDateTime
    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
     
    @PrePersist
    @PreUpdate
    private void validateDates() {
        if (expiresAt != null && issuedAt != null && expiresAt.isBefore(issuedAt)) {
            throw new IllegalStateException("Expiration date must be after or equal to the issued date.");
        }
    }
}