package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "access_logs")
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "digital_key_id", nullable = false)
    @JsonIgnoreProperties({"booking", "active"}) 
    private DigitalKey digitalKey;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    @JsonIgnoreProperties("bookings") 
    private Guest guest;

    @Column(nullable = false)
    private Timestamp accessTime;

    @Column(nullable = false)
    private String result; 

    private String reason;

    @PrePersist
    protected void onCreate() {
        if (this.accessTime == null) {
            this.accessTime = Timestamp.from(Instant.now());
        }
    }

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