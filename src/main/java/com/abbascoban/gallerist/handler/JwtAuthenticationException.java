package com.abbascoban.gallerist.handler;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg) {
        super(msg+" JWT geçersiz");
    }
}
