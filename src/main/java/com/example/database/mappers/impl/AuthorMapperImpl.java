package com.example.database.mappers.impl;

import com.example.database.domain.dto.AuthorDTO;
import com.example.database.domain.entities.Author;
import com.example.database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class AuthorMapperImpl implements Mapper<Author , AuthorDTO> {

    private final ModelMapper modelMapper;

    public AuthorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDTO mapTo(Author author) {
        return modelMapper.map(author , AuthorDTO.class);
    }

    @Override
    public Author mapFrom(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO , Author.class);
    }
}
