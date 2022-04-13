package com.holep.readingisgood.logging;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
@Data
@Builder
public class InfoLog {

    @Id
    private UUID id;

    private String endpoint;

    private String method;

    private String message;

    private String responseBody;

    private UUID authenticatedUser;

    private String ipAddress;

    private Date creationDate;
}
