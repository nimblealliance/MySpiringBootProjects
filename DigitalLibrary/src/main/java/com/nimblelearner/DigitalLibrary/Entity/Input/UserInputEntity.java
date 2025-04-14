package com.nimblelearner.DigitalLibrary.Entity.Input;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class UserInputEntity {

    private String firstName;
    private String lastName;
    private Instant dob;
    private UUID id;
    private String email;
    private long phoneNumber;
}
