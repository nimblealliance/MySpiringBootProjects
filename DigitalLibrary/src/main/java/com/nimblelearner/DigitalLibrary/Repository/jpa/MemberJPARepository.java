package com.nimblelearner.DigitalLibrary.Repository.jpa;

import com.nimblelearner.DigitalLibrary.Entity.Output.MembershipOutputEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJPARepository extends JpaRepository<MembershipOutputEntity,Long> {
}
