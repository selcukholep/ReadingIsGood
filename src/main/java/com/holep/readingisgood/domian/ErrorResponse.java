package com.holep.readingisgood.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class ErrorResponse {
    private String code;
    private String description;
}
