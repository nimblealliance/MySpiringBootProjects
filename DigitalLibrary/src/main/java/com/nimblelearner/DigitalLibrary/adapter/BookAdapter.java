package com.nimblelearner.DigitalLibrary.adapter;

import com.nimblelearner.DigitalLibrary.Commons.CommonAdapter;
import com.nimblelearner.DigitalLibrary.Entity.Input.BookInputEntity;
import com.nimblelearner.DigitalLibrary.Entity.Output.BookOutputEntity;
import com.nimblelearner.DigitalLibrary.Model.BookModel;
import com.nimblelearner.DigitalLibrary.Service.BookService;
import com.nimblelearner.DigitalLibrary.mappers.input.BookInputMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookAdapter implements CommonAdapter<BookInputEntity,BookModel, Long, BookOutputEntity> {

    private final BookInputMapper bookInputMapper;
    private final BookService bookService;

    @Autowired
    public BookAdapter(BookInputMapper bookInputMapper, BookService bookService) {
        this.bookInputMapper = bookInputMapper;
        this.bookService = bookService;
    }

    @Override
    public BookModel save(BookInputEntity inputEntity){
        return this.bookService.addBook(this.bookInputMapper.mapToModel(inputEntity));

    }

    @Override
    public BookModel getById(Long id){
        return this.bookService.getBookById(id);
    }

    @Override
    public boolean deleteById(Long id){
        return this.bookService.deleteById(id);
    }

    @Override
    public List<BookOutputEntity> findAll(){
        return this.bookService.findAllBooks();
    }

}
