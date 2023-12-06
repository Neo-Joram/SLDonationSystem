package com.yoramu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Patient {
    @Id
    private UUID id;
    private String names;
    private String location;
    private String sickness;
    private String treatment;
    private double amount;
    private String picture;
    private String description;
}
