package com.holep.readingisgood.data.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
@Data
@Builder
public class Order {

    @Id
    private UUID id;

    private UUID bookId;
    private UUID customerId;

    private int amount;
    private double price;
    private String status;

    private Date creationDate;
}
