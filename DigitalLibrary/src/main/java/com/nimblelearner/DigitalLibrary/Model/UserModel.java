package com.nimblelearner.DigitalLibrary.Model;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.time.Instant;
import java.util.UUID;


@Data
@Builder
@With
public class UserModel {

    private String firstName;
    private String lastName;
    private Instant dob;
    private UUID id;
    private String email;
    private long phoneNumber;

}
