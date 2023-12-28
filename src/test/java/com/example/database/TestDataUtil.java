package com.example.database;


import com.example.database.domain.dto.AuthorDTO;
import com.example.database.domain.dto.BookDTO;
import com.example.database.domain.entities.Author;
import com.example.database.domain.entities.Book;

public final class TestDataUtil {

    private TestDataUtil(){}

    public static Author createTestAuthor(){
        return Author.builder()
                .id(1L)
                .name("Suhaib Khater")
                .age(25)
                .build();
    }

    public static Author createTestAuthorB(){
        return Author.builder()
                .id(2L)
                .name("Ali Ahmad")
                .age(31)
                .build();
    }

    public static Author createTestAuthorC(){
        return Author.builder()
                .id(3L)
                .name("Omar Nasr")
                .age(27)
                .build();
    }

    public static Author createTestAuthorD(){
        return Author.builder()
                .id(4L)
                .name("Osama Abed")
                .age(29)
                .build();
    }

    public static Book createTestBook(final Author author){
        return Book.builder()
                .isbn("123-456")
                .title("Six hats")
                .author(author)
                .build();
    }

    public static BookDTO createTestBookDTO(final AuthorDTO author){
        return BookDTO.builder()
                .isbn("123-456")
                .title("Six hats")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author){
        return Book.builder()
                .isbn("679-101")
                .title("None")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author){
        return Book.builder()
                .isbn("103-204")
                .title("Life is good")
                .author(author)
                .build();
    }
}
