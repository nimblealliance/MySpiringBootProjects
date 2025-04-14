package com.nimblelearner.DigitalLibrary.Entity.Output;

import com.nimblelearner.DigitalLibrary.enums.MembershipStatus;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "membership")
public class MembershipOutputEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserOutputEntity user;

    private Instant startDate;
    private Instant endDate;
    private MembershipStatus status;

}