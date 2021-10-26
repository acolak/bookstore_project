package com.acolak.readingisgood.repository;

import com.acolak.readingisgood.repository.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author AhmetColak date 26.10.2021 Copyright © 2021.
 **/
public interface BookRepository extends MongoRepository<Book, String> {

	Optional<Book> findBookByName(String name);

}
