package com.holep.readingisgood.data.repository;

import com.holep.readingisgood.data.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends MongoRepository<Book, UUID> {

}
