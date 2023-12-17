package com.yoramu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Patient {
    @Id
    private UUID id;
    @NotBlank(message = "names must not be blank")
    private String names;
    @NotBlank(message = "location must not be blank")
    private String location;
    @NotBlank(message = "sickness must not be blank")
    private String sickness;
    @NotBlank(message = "treatment must not be blank")
    private String treatment;
    private double amount;
    @NotBlank(message = "description must not be blank")
    private String description;
}
