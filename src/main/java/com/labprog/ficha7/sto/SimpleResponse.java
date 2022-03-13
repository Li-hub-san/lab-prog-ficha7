package com.labprog.ficha7.sto;

public class SimpleResponse<T> {
    private boolean status;
    private String message;
    private T data;

    public SimpleResponse(boolean statusOk, String message, T data) {
        this.status = statusOk;
        this.message = message;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
