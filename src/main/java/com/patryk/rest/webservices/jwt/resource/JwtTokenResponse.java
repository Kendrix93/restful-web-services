package com.patryk.rest.webservices.jwt.resource;

import java.io.Serial;
import java.io.Serializable;

public record JwtTokenResponse(String token) implements Serializable {

    @Serial
    private static final long serialVersionUID = 8317676219297719109L;

    public String getToken() {
        return this.token;
    }
}
