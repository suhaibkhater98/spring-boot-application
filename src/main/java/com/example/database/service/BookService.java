package com.example.database.service;

import com.example.database.domain.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book save(String isbn , Book book);

    List<Book> findAll();

    Page<Book> findAll(Pageable pageable);

    Optional<Book> getBook(String isbn);

    boolean isExist(String isbn);

    void delete(String isbn);
}
