package com.example.demo.model;

import java.security.Timestamp;

public class KeyShareRequest {
    private long id;
    private DigitalKey digitalKey;
    private Guest sharedBy;
    private Guest sharedWith;
    private Timestamp shareStart;
    private Timestamp shareEnd;
    private String status;
    private Timestamp createdAt;
    public KeyShareRequest(DigitalKey digitalKey, Guest sharedBy, Guest sharedWith, Timestamp shareStart,
            Timestamp shareEnd, String status, Timestamp createdAt) {
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
    public void setShareStart(Timestamp shareStart) {
        this.shareStart = shareStart;
    }
    public void setShareEnd(Timestamp shareEnd) {
        this.shareEnd = shareEnd;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
