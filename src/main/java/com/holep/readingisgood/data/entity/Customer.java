package com.holep.readingisgood.data.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@Document
public class Customer {

    @Id
    private UUID id;

    private String name;
    private String surname;

    private String email;
    private String password;
}
