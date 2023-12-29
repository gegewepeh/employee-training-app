package com.idstar.employeeApp.dto;

import com.idstar.employeeApp.model.Employee;
import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapper {
    private int code;
    private Object data;
    private String status;

    public ResponseWrapper(int code, Object obj) {
        this.code = code;
        this.data = obj;
        this.status = "fail";
    }

    public ResponseWrapper success() {
        this.status = "sukses";

        return this;
    }
}
