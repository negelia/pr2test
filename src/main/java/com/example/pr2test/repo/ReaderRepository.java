package com.example.pr2test.repo;

import com.example.pr2test.models.Reader;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReaderRepository extends CrudRepository<Reader, Long> {

    //List<Post> findBySurname(String title);
    List<Reader> findBySurnameContains(String surname);
}

