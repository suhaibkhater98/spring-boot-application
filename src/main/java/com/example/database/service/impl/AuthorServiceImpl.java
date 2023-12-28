package com.example.database.service.impl;

import com.example.database.domain.entities.Author;
import com.example.database.repo.AuthorRepo;
import com.example.database.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class AuthorServiceImpl implements AuthorService {


    private AuthorRepo authorRepo;

    @Autowired
    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public List<Author> findAll() {
        return StreamSupport.stream(authorRepo.findAll().spliterator() , false).collect(Collectors.toList());
    }

    @Override
    public Optional<Author> getAuthor(long id) {
        return authorRepo.findById(id);
    }

    @Override
    public boolean isExist(long id) {
        return authorRepo.existsById(id);
    }

    @Override
    public void delete(long id) {
        authorRepo.deleteById(id);
    }
}
