package com.foxminded.vitaliifedan.task10.exceptions;

public class LectureException extends RuntimeException {
    public LectureException(String message) {
        super(message);
    }

    public LectureException(Throwable cause) {
        super(cause);
    }
}
