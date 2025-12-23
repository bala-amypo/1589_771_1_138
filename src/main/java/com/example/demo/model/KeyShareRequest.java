package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "key_share_requests")
public class KeyShareRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "digital_key_id", nullable = false)
    @JsonIgnoreProperties({"booking", "active", "keyValue"}) 
    private DigitalKey digitalKey;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shared_by_id", nullable = false)
    @JsonIgnoreProperties({"bookings", "role", "email"}) 
    private Guest sharedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shared_with_id", nullable = false)
    @JsonIgnoreProperties({"bookings", "role", "email"}) 
    private Guest sharedWith;

    @Column(nullable = false)
    private Timestamp shareStart;

    @Column(nullable = false)
    private Timestamp shareEnd;

    @Column(nullable = false)
    private String status = "PENDING"; 

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        if (this.status == null) this.status = "PENDING";
    }

    public KeyShareRequest() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DigitalKey getDigitalKey() { return digitalKey; }
    public void setDigitalKey(DigitalKey digitalKey) { this.digitalKey = digitalKey; }

    public Guest getSharedBy() { return sharedBy; }
    public void setSharedBy(Guest sharedBy) { this.sharedBy = sharedBy; }

    public Guest getSharedWith() { return sharedWith; }
    public void setSharedWith(Guest sharedWith) { this.sharedWith = sharedWith; }

    public Timestamp getShareStart() { return shareStart; }
    public void setShareStart(Timestamp shareStart) { this.shareStart = shareStart; }

    public Timestamp getShareEnd() { return shareEnd; }
    public void setShareEnd(Timestamp shareEnd) { this.shareEnd = shareEnd; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
}