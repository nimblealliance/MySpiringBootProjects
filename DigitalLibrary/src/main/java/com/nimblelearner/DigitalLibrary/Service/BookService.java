package com.nimblelearner.DigitalLibrary.Service;

import com.nimblelearner.DigitalLibrary.Entity.Output.BookOutputEntity;
import com.nimblelearner.DigitalLibrary.Model.BookModel;
import com.nimblelearner.DigitalLibrary.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookModel addBook(BookModel book){
        return this.bookRepository.save(book);
    }

    public BookModel getBookById(long id){
        return this.bookRepository.findById(id);
    }

    public boolean deleteById(long id){

        if( getBookById(id)!= null){
            this.bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<BookOutputEntity> findAllBooks(){
        return this.bookRepository.findAllBooks();
    }

}
