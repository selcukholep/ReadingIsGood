package com.holep.readingisgood.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
public class CustomerDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7712922438311037958L;

    @Null
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonDeserialize
    private UUID id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Email
    private String email;

    @JsonIgnore
    @Null
    @Parameter(hidden = true)
    private String password;

    @NonNull
    private String name;

    @NonNull
    private String surname;

}
