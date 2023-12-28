package com.example.database.controller;


import com.example.database.domain.dto.AuthorDTO;
import com.example.database.domain.entities.Author;
import com.example.database.mappers.Mapper;
import com.example.database.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private AuthorService authorService;
    private Mapper<Author , AuthorDTO> authorMapper;

    @Autowired
    public AuthorController(AuthorService authorService , Mapper<Author , AuthorDTO> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO author){
        Author savedAuthor = authorService.save(authorMapper.mapFrom(author));
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthor) , HttpStatus.CREATED);
    }

    @GetMapping(path = "/authors")
    public List<AuthorDTO> listAuthors(){
        List<Author> authors = authorService.findAll();
        return authors.stream()
                .map(authorMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable long id){
        Optional<Author> author = authorService.getAuthor(id);
        AuthorDTO authorDTO = author.map(value -> authorMapper.mapTo(value)).orElse(null);
        if(authorDTO == null)
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(authorDTO , HttpStatus.OK);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable long id , @RequestBody AuthorDTO author){

        if(!authorService.isExist(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        author.setId(id);
        Author authorData = authorMapper.mapFrom(author);
        return new ResponseEntity<>(authorMapper.mapTo(authorService.save(authorData)) , HttpStatus.OK);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity deleteAuthor(@PathVariable long id){
        authorService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
