package com.zerod.authdemo.exceptions;

/**
 * User not found exception
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
