package com.foxminded.vitaliifedan.task10.exceptions;

public class GroupException extends RuntimeException {
    public GroupException(String message) {
        super(message);
    }

    public GroupException(Throwable cause) {
        super(cause);
    }
}
