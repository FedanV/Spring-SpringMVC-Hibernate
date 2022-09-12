package com.foxminded.vitaliifedan.task10.exceptions;

public class CourseException extends RuntimeException {
    public CourseException(String message) {
        super(message);
    }

    public CourseException(Throwable cause) {
        super(cause);
    }
}
