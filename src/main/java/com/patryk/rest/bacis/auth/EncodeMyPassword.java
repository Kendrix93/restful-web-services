package com.patryk.rest.bacis.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeMyPassword {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String asdf = encoder.encode("asdf");
        System.out.println(asdf);
    }

}
