package com.yoramu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Donation {
    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;
    private double amount;
    private String email;
}
