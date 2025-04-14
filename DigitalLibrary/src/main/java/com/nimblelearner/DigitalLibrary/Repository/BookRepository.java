package com.nimblelearner.DigitalLibrary.Repository;

import com.nimblelearner.DigitalLibrary.Entity.Output.BookOutputEntity;
import com.nimblelearner.DigitalLibrary.Model.BookModel;
import com.nimblelearner.DigitalLibrary.Repository.jpa.BookJPARepository;
import com.nimblelearner.DigitalLibrary.mappers.output.BookOutputMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookRepository {


    private final BookJPARepository bookJPARepository;
    private final BookOutputMapper bookOutputMapper;

    @Autowired
    public BookRepository(BookJPARepository bookJPARepository, BookOutputMapper bookOutputMapper) {
        this.bookJPARepository = bookJPARepository;
        this.bookOutputMapper = bookOutputMapper;
    }

    public List<BookOutputEntity> findAllBooks(){
        return this.bookJPARepository.findAll();
    }

    public BookModel save(BookModel book){
        BookOutputEntity outputEntity = this.bookOutputMapper.mapFromModel(book);
        BookOutputEntity savedOutputEntity = bookJPARepository.save(outputEntity);
        return this.bookOutputMapper.mapToModel(savedOutputEntity);
    }

    public BookModel findById(long id){

        Optional<BookOutputEntity> optionalBookOutputEntity = this.bookJPARepository.findById(id);
        return optionalBookOutputEntity.map(this.bookOutputMapper::mapToModel).orElse(null);
    }

    public void deleteById(long id){
        this.bookJPARepository.deleteById(id);
    }

}
