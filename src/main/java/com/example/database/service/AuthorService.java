package com.example.database.service;


import com.example.database.domain.entities.Author;

import java.util.List;
import java.util.Optional;


public interface AuthorService {

    Author save(Author author);

    List<Author> findAll();

    Optional<Author> getAuthor(long id);

    boolean isExist(long id);

    void delete(long id);
}
