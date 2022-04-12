package com.holep.readingisgood.data.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
@Builder
public class Book {

    @Id
    private UUID id;

    private String name;
    private String authorName;

    private int stock;
    private double price;

    @Version
    private Long version;
}
