package com.holep.readingisgood.domian.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class LoginSucceedResponse {

    private String token;

}
