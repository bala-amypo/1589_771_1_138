package com.example.demo.models;
public class Guest {
    private long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Boolean verified;
    private Boolean active;
    private String role;
    private Timestamp createdAt;
    public Guest(){}
    public Guest(Long id,String fullName,String email,String phoneNumber,Boolean verified,Boolean active,String role,Timestamp createdAt){
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.verified = verified;
        this.active = active;
        this.role = role;
        this.createdAt = createdAt;
    }
}
