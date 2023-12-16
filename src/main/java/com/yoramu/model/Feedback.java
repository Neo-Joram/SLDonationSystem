package com.yoramu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Feedback {
    @Id
    private UUID id;
    @NotBlank(message = "email must not be blank")
    @Email
    private String email;
    @NotBlank(message = "message must not be blank")
    private String message;
}
