package com.yarikonen.web4.Exceptions;

public class AuthException extends RuntimeException {

        public AuthException(String msg) {
            super("Authentification error : " + msg);
        }
    }
