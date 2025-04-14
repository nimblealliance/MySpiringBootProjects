package com.nimblelearner.DigitalLibrary.Repository.jpa;

import com.nimblelearner.DigitalLibrary.Entity.Output.UserOutputEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJPARepository extends JpaRepository<UserOutputEntity, UUID> {
}
