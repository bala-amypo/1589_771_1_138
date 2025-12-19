package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "access_logs")
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Long, PK

    @ManyToOne
    @JoinColumn(name = "digital_key_id", nullable = false)
    private DigitalKey digitalKey; // ManyToOne DigitalKey

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest; // ManyToOne Guest

    @Column(nullable = false)
    private Timestamp accessTime; // Timestamp

    @Column(nullable = false)
    private String result; // SUCCESS / DENIED

    private String reason; // String

    public AccessLog() {}

    /**
     * Requirement: accessTime cannot be in the future.
     */
    @PrePersist
    @PreUpdate
    private void validateAccessTime() {
        if (this.accessTime != null && this.accessTime.after(Timestamp.from(Instant.now()))) {
            throw new IllegalArgumentException("Access time cannot be in the future."); //
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DigitalKey getDigitalKey() { return digitalKey; }
    public void setDigitalKey(DigitalKey digitalKey) { this.digitalKey = digitalKey; }

    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }

    public Timestamp getAccessTime() { return accessTime; }
    public void setAccessTime(Timestamp accessTime) { this.accessTime = accessTime; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}