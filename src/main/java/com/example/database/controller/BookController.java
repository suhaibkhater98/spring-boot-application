package com.example.database.controller;


import com.example.database.domain.dto.BookDTO;
import com.example.database.domain.entities.Book;
import com.example.database.mappers.Mapper;
import com.example.database.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookController {


    final private Mapper<Book, BookDTO> bookMapper;
    final private BookService bookService;


    public BookController(Mapper<Book, BookDTO> bookMapper , BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDTO> createBook(@PathVariable("isbn") String isbn , @RequestBody BookDTO bookDTO){
        HttpStatus created = HttpStatus.CREATED;
        if(bookService.isExist(isbn)){
            created = HttpStatus.OK;
        }
        Book book = bookMapper.mapFrom(bookDTO);
        BookDTO createdBook = bookMapper.mapTo(bookService.save(isbn , book));
        return new ResponseEntity<>(createdBook , created);
    }

    @GetMapping("/books")
    public Page<BookDTO> getListBook(Pageable pageable){
        Page<Book> books = bookService.findAll(pageable);
        return books.map(bookMapper::mapTo);
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDTO> getBook(@PathVariable String isbn){
        Optional<Book> book = bookService.getBook(isbn);
        return book.map(bookEntity -> new ResponseEntity<>(bookMapper.mapTo(bookEntity) , HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/books/{isbn}")
    public ResponseEntity deleteBook(@PathVariable String isbn){
        bookService.delete(isbn);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
