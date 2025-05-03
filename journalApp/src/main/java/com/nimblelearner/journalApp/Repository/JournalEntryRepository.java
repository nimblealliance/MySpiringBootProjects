package com.nimblelearner.journalApp.Repository;

import com.nimblelearner.journalApp.Entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry , Long> {
}
