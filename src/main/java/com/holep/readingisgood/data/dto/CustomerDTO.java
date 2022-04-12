package com.holep.readingisgood.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class CustomerDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7712922438311037958L;

    @Null
    private String id;

    @JsonIgnore
    @Email
    private String email;

    @JsonIgnore
    @Null
    private String password;

    @NonNull
    private String name;

    @NonNull
    private String surname;

}
