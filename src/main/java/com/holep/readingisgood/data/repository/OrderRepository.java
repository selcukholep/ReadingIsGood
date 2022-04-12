package com.holep.readingisgood.data.repository;

import com.holep.readingisgood.data.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface OrderRepository extends MongoRepository<Order, UUID> {

    Page<Order> findAllByCustomerId(UUID customerId, Pageable pageable);
    Page<Order> findAllByCreationDateBetween(Date startDate, Date endDate, Pageable pageable);
}
