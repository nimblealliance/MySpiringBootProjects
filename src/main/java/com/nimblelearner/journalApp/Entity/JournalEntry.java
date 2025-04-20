package com.nimblelearner.journalApp.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Entity
@Table(name="journal_entries")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString


public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    private String title;

    private String content;

    private LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);

}
