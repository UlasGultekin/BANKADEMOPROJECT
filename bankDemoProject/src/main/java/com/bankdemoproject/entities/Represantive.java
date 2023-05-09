package com.bankdemoproject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Represantive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repId;
    private String name;
    private String surName;
    private String email;
    private String tcNo;
    private String ybNo;
    private String photo;
    private String phoneNo;
    private String contactAddres;
    private String represantiveType;
    private String branchOffice;
    private String topManager;



}
