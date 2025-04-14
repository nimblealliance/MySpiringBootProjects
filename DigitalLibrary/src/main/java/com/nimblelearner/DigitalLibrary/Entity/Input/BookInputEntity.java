package com.nimblelearner.DigitalLibrary.Entity.Input;

import lombok.Data;

import java.time.Instant;

@Data
public class BookInputEntity {

    private long id;
    private String name;
    private String author;
    private String description;
    private Instant publishedDate;
    private Instant createdAt;
    private Instant updatedAt;

}
