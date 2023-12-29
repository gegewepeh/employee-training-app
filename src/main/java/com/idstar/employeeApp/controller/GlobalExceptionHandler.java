package com.idstar.employeeApp.controller;

import com.idstar.employeeApp.dto.ErrorWrapper.AppError;
import com.idstar.employeeApp.dto.ErrorWrapper.ValidationErrorWrapper;
import com.idstar.employeeApp.dto.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "general validation error");
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseWrapper> handleConflictDB(DataIntegrityViolationException e) {
        e.printStackTrace();
        AppError appError = new AppError(HttpStatus.BAD_REQUEST, "DB Validation Error");
        ResponseWrapper wrapper = new ResponseWrapper(appError.code, appError.getMessage());
        return new ResponseEntity<>(wrapper, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ResponseWrapper> HandleValidationError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String validationErrs = ValidationErrorWrapper.WrapError(bindingResult.getAllErrors());
            AppError appError = new AppError(HttpStatus.BAD_REQUEST, validationErrs);
            ResponseWrapper wrapper = new ResponseWrapper(appError.code, appError.getMessage());
            return new ResponseEntity<>(wrapper, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}