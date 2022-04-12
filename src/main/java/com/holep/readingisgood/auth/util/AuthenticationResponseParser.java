package com.holep.readingisgood.auth.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AuthenticationResponseParser {

    public static <T> void prepareResponse(HttpServletResponse response, int status, T body) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getOutputStream().write(new Gson().toJson(body)
                .getBytes(StandardCharsets.UTF_8));
    }

}
