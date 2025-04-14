package com.nimblelearner.DigitalLibrary.Entity.Output;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Builder
@Setter
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
public class BookOutputEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "published_date")
    private Instant publishedDate;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}
