package com.holep.readingisgood.data.repository;

import com.holep.readingisgood.data.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, UUID> {

    Optional<Customer> findByEmail(String email);
}
