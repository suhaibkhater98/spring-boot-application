package com.example.database.service.impl;

import com.example.database.domain.entities.Book;
import com.example.database.repo.BookRepo;
import com.example.database.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class BookServiceImpl implements BookService {

    private BookRepo bookRepo;

    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Book save(String isbn, Book book) {
        book.setIsbn(isbn);
        return bookRepo.save(book);
    }

    @Override
    public List<Book> findAll() {
        return StreamSupport.stream(bookRepo.findAll().spliterator() , false).collect(Collectors.toList());
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepo.findAll(pageable);
    }

    @Override
    public Optional<Book> getBook(String isbn) {
        return bookRepo.findById(isbn);
    }

    @Override
    public boolean isExist(String isbn) {
        return bookRepo.existsById(isbn);
    }

    @Override
    public void delete(String isbn) {
        bookRepo.deleteById(isbn);
    }
}
