package com.nimblelearner.DigitalLibrary.mappers.input;

import com.nimblelearner.DigitalLibrary.Entity.Input.BookInputEntity;
import com.nimblelearner.DigitalLibrary.Model.BookModel;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class BookInputMapper {

    public BookModel mapToModel(BookInputEntity inputEntity){
        return BookModel.builder()
                .id(inputEntity.getId())
                .name(inputEntity.getName())
                .author(inputEntity.getAuthor())
                .description(inputEntity.getDescription())
                .publishedDate(inputEntity.getPublishedDate())
                .createdAt(Instant.now())
                .build();
    }
}
