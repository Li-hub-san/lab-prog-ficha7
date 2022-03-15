package com.labprog.ficha7.utils;

public class SimpleException extends Exception {
    private final ExceptionCode code;
    private final String message;

    public SimpleException(ExceptionCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ExceptionCode getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
