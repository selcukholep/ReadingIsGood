package com.holep.readingisgood.data.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class OrderDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5955276279069084415L;

    @Null
    private String id;

    @NonNull
    private String bookId;

    @Null
    private String customerId;

    @Min(1)
    private int amount;

    @Null
    private double price;

    @Null
    private String status;

    @Null
    private Date creationDate;
}
