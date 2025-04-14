package com.nimblelearner.DigitalLibrary.Repository.jpa;

import com.nimblelearner.DigitalLibrary.Entity.Output.BookOutputEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJPARepository extends JpaRepository<BookOutputEntity,Long> {
}
