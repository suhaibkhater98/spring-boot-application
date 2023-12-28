package com.example.database.mappers.impl;

import com.example.database.domain.dto.BookDTO;
import com.example.database.domain.entities.Book;
import com.example.database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class BookMapperImpl implements Mapper<Book , BookDTO> {

    private final ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDTO mapTo(Book book) {
        return modelMapper.map(book , BookDTO.class);
    }

    @Override
    public Book mapFrom(BookDTO bookDTO) {
        return modelMapper.map(bookDTO , Book.class);
    }
}
