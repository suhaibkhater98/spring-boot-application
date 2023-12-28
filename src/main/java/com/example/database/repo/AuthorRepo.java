package com.example.database.repo;

import com.example.database.domain.entities.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends CrudRepository<Author , Long> {

    Iterable<Author> ageLessThan(int age);

    @Query("select a from Author a where a.age > ?1")
    Iterable<Author> findAuthorsWithAgeGreaterThan(int age);
}
