package com.example.demo.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
@Entity
public class KeyShareRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private DigitalKey digitalKey;
    @ManyToOne
    private Guest sharedBy;
    @ManyToOne
    private Guest sharedWith;
    private LocalDateTime shareStart;
    private LocalDateTime shareEnd;
    private String status;
    private LocalDateTime createdAt;
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
    public long getId() {
        return id;
    }
    public DigitalKey getDigitalKey() {
        return digitalKey;
    }
    public Guest getSharedBy() {
        return sharedBy;
    }
    public Guest getSharedWith() {
        return sharedWith;
    }
    public Timestamp getShareStart() {
        return shareStart;
    }
    public Timestamp getShareEnd() {
        return shareEnd;
    }
    public String getStatus() {
        return status;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setDigitalKey(DigitalKey digitalKey) {
        this.digitalKey = digitalKey;
    }
    public void setSharedBy(Guest sharedBy) {
        this.sharedBy = sharedBy;
    }
    public void setSharedWith(Guest sharedWith) {
        this.sharedWith = sharedWith;
    }
    public void setShareStart(LocalDateTime shareStart) {
        this.shareStart = shareStart;
    }
    public void setShareEnd(LocalDateTime shareEnd) {
        this.shareEnd = shareEnd;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
