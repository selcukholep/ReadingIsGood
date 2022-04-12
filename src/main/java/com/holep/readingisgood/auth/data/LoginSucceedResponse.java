package com.holep.readingisgood.auth.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class LoginSucceedResponse {

    private String token;

}
