package com.example.database.repo;

import com.example.database.domain.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepo extends CrudRepository<Book, String> , PagingAndSortingRepository<Book , String> {
}
