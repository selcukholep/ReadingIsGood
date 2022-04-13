package com.holep.readingisgood.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.holep.readingisgood.data.constant.OrderDetail;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class OrderDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5955276279069084415L;

    @Null
    @JsonDeserialize
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @Null
    @JsonDeserialize
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID customerId;

    @Null
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;

    @Null
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date creationDate;

    @Valid
    private Set<OrderDetail> details;
}
