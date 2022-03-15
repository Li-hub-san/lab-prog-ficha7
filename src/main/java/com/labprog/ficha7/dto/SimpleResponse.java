package com.labprog.ficha7.dto;

public class SimpleResponse {
    protected boolean status;
    protected String message;

    public SimpleResponse() {
        this.status = false;
        this.message = "Ocorreu um erro";
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
