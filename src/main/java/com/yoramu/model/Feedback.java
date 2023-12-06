package com.yoramu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Feedback {
    @Id
    private UUID id;
    private String email;
    private String message;
}
