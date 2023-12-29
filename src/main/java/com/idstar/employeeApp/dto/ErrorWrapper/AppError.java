package com.idstar.employeeApp.dto.ErrorWrapper;

import org.springframework.http.HttpStatus;

public class AppError extends Exception {
    public HttpStatus httpStatus;
    public int code;

    public AppError(HttpStatus httpStatus, String s) {
        super(s);
        this.httpStatus = httpStatus;
        this.code = httpStatus.value();
    }
}
