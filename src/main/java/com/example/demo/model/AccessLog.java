package com.example.demo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;

@Entity
@Table(name = "access_logs")
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "key_id", nullable = false)
    @JsonIgnoreProperties({"booking", "active"}) // Simplifies the key info in the log
    private DigitalKey digitalKey;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    @JsonIgnoreProperties("bookings")
    private Guest guest;

    @Column(nullable = false)
    private Timestamp accessTime;

    @Column(nullable = false)
    private String result; // e.g., SUCCESS, FAILURE

    private String reason; // e.g., Valid Key, Expired Key, Wrong Room

    public AccessLog() {}

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