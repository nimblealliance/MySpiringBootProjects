package com.nimblelearner.DigitalLibrary.Controller;

import com.nimblelearner.DigitalLibrary.Commons.CommonAdapter;
import com.nimblelearner.DigitalLibrary.Entity.Input.BookInputEntity;
import com.nimblelearner.DigitalLibrary.Entity.Output.BookOutputEntity;
import com.nimblelearner.DigitalLibrary.Model.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final CommonAdapter<BookInputEntity,BookModel,Long,BookOutputEntity> bookAdapter;

    @Autowired
    public BookController(CommonAdapter<BookInputEntity,BookModel,Long,BookOutputEntity> bookAdapter) {
        this.bookAdapter = bookAdapter;
    }

    @PostMapping("/add-book")
    public ResponseEntity<?> addBook(@RequestBody BookInputEntity book){
        return new ResponseEntity<BookModel>(this.bookAdapter.save(book),HttpStatus.CREATED);
    }

    @GetMapping("/get-book")
    public ResponseEntity<?> getBookById(@RequestParam Long id){
        if (this.bookAdapter.getById(id)!=null) {
            return new ResponseEntity<BookModel>(this.bookAdapter.getById(id),HttpStatus.OK );
        }
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/del-book")
    public ResponseEntity<?> deleteById(@RequestParam long id){
        if (this.bookAdapter.deleteById(id)) {
            return new ResponseEntity<Boolean>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("/update-book")
    public ResponseEntity<?> updateBookById(@RequestParam long id , @RequestBody BookInputEntity book){
        if (this.bookAdapter.getById(id)!=null) {

            return new ResponseEntity<BookModel>(this.bookAdapter.save(book),HttpStatus.OK );
        }
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks(){
        return new ResponseEntity<List<BookOutputEntity>>( this.bookAdapter.findAll(),HttpStatus.OK );
    }

}
