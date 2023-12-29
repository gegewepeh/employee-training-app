package com.idstar.employeeApp.dto.ErrorWrapper;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidationErrorWrapper {
    public static String WrapError(List<ObjectError> errors) {
        StringBuilder wrappedString = new StringBuilder();
        for (ObjectError error : errors) {
            if (!wrappedString.isEmpty()) {
                wrappedString.append(", ");
            }
            wrappedString.append(error.getDefaultMessage());
        }

        return wrappedString.toString();
    }
}
