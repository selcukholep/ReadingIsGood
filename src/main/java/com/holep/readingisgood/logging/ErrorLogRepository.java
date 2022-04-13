package com.holep.readingisgood.logging;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ErrorLogRepository extends MongoRepository<ErrorLog, UUID> {
}
