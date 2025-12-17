package com.example.demo.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class KeyShareRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "digital_key_id")
    private DigitalKey digitalKey;

    @ManyToOne
    @JoinColumn(name = "shared_by_guest_id")
    private Guest sharedBy;

    @ManyToOne
    @JoinColumn(name = "shared_with_guest_id")
    private Guest sharedWith;

    private LocalDateTime shareStart;
    private LocalDateTime shareEnd;
    private String status;
    private LocalDateTime createdAt;

    // 1. JPA MUST have a no-args constructor
    public KeyShareRequest() {}

    public KeyShareRequest(DigitalKey digitalKey, Guest sharedBy, Guest sharedWith, LocalDateTime shareStart,
                          LocalDateTime shareEnd, String status, LocalDateTime createdAt) {
        this.digitalKey = digitalKey;
        this.sharedBy = sharedBy;
        this.sharedWith = sharedWith;
        this.shareStart = shareStart;
        this.shareEnd = shareEnd;
        this.status = status;
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 2. FIXED: All Return types changed from Timestamp to LocalDateTime
    public long getId() { return id; }
    public DigitalKey getDigitalKey() { return digitalKey; }
    public Guest getSharedBy() { return sharedBy; }
    public Guest getSharedWith() { return sharedWith; }
    public LocalDateTime getShareStart() { return shareStart; }
    public LocalDateTime getShareEnd() { return shareEnd; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Standard Setters
    public void setId(long id) { this.id = id; }
    public void setDigitalKey(DigitalKey digitalKey) { this.digitalKey = digitalKey; }
    public void setSharedBy(Guest sharedBy) { this.sharedBy = sharedBy; }
    public void setSharedWith(Guest sharedWith) { this.sharedWith = sharedWith; }
    public void setShareStart(LocalDateTime shareStart) { this.shareStart = shareStart; }
    public void setShareEnd(LocalDateTime shareEnd) { this.shareEnd = shareEnd; }
    public void setStatus(String status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}