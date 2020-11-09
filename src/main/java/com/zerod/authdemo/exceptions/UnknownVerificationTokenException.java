package com.zerod.authdemo.exceptions;

public class UnknownVerificationTokenException extends Exception {
    public UnknownVerificationTokenException(String error) {
        super(error);
    }
}
