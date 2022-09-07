package com.example.pr2test.repo;

import com.example.pr2test.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    //List<Book> findByTitle(String title);
    List<Book> findByTitleContains(String title);
}
