package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "digital_key_id", nullable = false)
    private DigitalKey digitalKey;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    private LocalDateTime accessTime;
    private String result;
    private String reason;

    public AccessLog() {}

    public AccessLog(DigitalKey digitalKey, Guest guest, LocalDateTime accessTime, String result, String reason) {
        this.digitalKey = digitalKey;
        this.guest = guest;
        setAccessTime(accessTime);
        this.result = result;
        this.reason = reason;
    }

    // --- ALIAS METHODS TO FIX SERVICE ERRORS ---
    public DigitalKey getKey() {
        return this.digitalKey;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.accessTime = timestamp;
    }

    // --- STANDARD GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DigitalKey getDigitalKey() { return digitalKey; }
    public void setDigitalKey(DigitalKey digitalKey) { this.digitalKey = digitalKey; }

    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }

    public LocalDateTime getAccessTime() { return accessTime; }
    public void setAccessTime(LocalDateTime accessTime) {
        if(accessTime != null && accessTime.isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("Access time cannot be in the future");
        }
        this.accessTime = accessTime;
    }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}