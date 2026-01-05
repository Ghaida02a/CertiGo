package com.codeline.CertiGo.Exceptions;
import org.springframework.http.HttpStatus;

public class CustomException extends Exception{
    private final Integer status;

    public CustomException(String message, int status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return HttpStatus.valueOf(this.status);
    }
}
