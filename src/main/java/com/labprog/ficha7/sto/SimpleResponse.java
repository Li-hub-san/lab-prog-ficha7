package com.labprog.ficha7.sto;

public class SimpleResponse<T> {
    private boolean statusOk;
    private String message;
    private T data;

    public SimpleResponse(boolean statusOk, String message, T data) {
        this.statusOk = statusOk;
        this.message = message;
        this.data = data;
    }

    public boolean isStatusOk() {
        return statusOk;
    }

    public void setStatusOk(boolean statusOk) {
        this.statusOk = statusOk;
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
