package edu.upb.pumatiti.models.repository;

public class Base {

    private boolean success;

    private String message;

    private Exception exception;

    private Object data;

    public Base(Object data) {
        this.success = true;
        this.message = "";
        this.exception = null;
        this.data = data;
    }

    public Base(String message) {
        this.success = false;
        this.message = message;
        this.exception = null;
        this.data = null;
    }

    public Base(String message, Exception exception) {
        this.success = false;
        this.message = message;
        this.exception = exception;
        this.data = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
