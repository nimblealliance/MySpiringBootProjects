package com.nimblelearner.DigitalLibrary.Model;

import com.nimblelearner.DigitalLibrary.Entity.Output.UserOutputEntity;
import com.nimblelearner.DigitalLibrary.enums.MembershipStatus;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.time.Instant;

@Data
@Builder
@With
public class MemberModel {

    private long id;
    private UserOutputEntity user;
    private Instant startDate;
    private Instant endDate;
    private MembershipStatus status;
}
