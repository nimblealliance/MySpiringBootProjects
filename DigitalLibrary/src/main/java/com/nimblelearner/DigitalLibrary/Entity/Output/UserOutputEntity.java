package com.nimblelearner.DigitalLibrary.Entity.Output;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Builder
@Setter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserOutputEntity {

    @Id
    @UuidGenerator
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false,name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private Instant dob;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false , name="phone_number")
    private long phoneNumber;
}
