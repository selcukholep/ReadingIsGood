package com.holep.readingisgood.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class CustomerDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7712922438311037958L;

    private String id;

    @Email
    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private String name;
    private String surname;
}
