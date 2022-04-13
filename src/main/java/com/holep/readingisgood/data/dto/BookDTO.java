package com.holep.readingisgood.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
public class BookDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6103298524255270700L;

    @Null
    @JsonDeserialize
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NonNull
    private String name;
    private String authorName;

    @Min(0)
    private int stock;

    @Min(0)
    private double price;

    @Null
    @JsonIgnore
    private Long version;
}
