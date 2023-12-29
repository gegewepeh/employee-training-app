package com.idstar.employeeApp.controller;

import com.idstar.employeeApp.dto.Employee.CreateEmployeeRequest;
import com.idstar.employeeApp.dto.DeleteRequest;
import com.idstar.employeeApp.dto.ErrorWrapper.AppError;
import com.idstar.employeeApp.dto.ResponseWrapper;
import com.idstar.employeeApp.dto.Employee.UpdateEmployeeRequest;
import com.idstar.employeeApp.model.Employee;
import com.idstar.employeeApp.service.Impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/idstar/karyawan")
public class EmployeeController {
    @Autowired
    public EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseWrapper> getById(@PathVariable long id) {
        Optional<Employee> employee = employeeServiceImpl.get(id);
        ResponseWrapper wrapper = new ResponseWrapper(200, employee).success();
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<ResponseWrapper> getList(@RequestParam int page, @RequestParam int size) {
        Page<Employee> obj = employeeServiceImpl.getAll(page, size);
        ResponseWrapper wrapper = new ResponseWrapper(200, obj).success();
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseWrapper> create(@Valid @RequestBody CreateEmployeeRequest reqObject, BindingResult bindingResult) {
        ResponseEntity<ResponseWrapper> errorValidation = GlobalExceptionHandler.HandleValidationError(bindingResult);
        if (errorValidation != null) {
            return errorValidation;
        }
        try {
            Employee obj = employeeServiceImpl.create(reqObject);
            ResponseWrapper wrapper = new ResponseWrapper(200, obj).success();
            return new ResponseEntity<>(wrapper, HttpStatus.OK);
        } catch (AppError e) {
            ResponseWrapper wrapper = new ResponseWrapper(e.code, e.getMessage());
            return new ResponseEntity<>(wrapper, e.httpStatus);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseWrapper> update(@Valid @RequestBody UpdateEmployeeRequest reqObject, BindingResult bindingResult) {
        ResponseEntity<ResponseWrapper> errorValidation = GlobalExceptionHandler.HandleValidationError(bindingResult);
        if (errorValidation != null) {
            return errorValidation;
        }
        try {
            Employee obj = employeeServiceImpl.update(reqObject);
            ResponseWrapper wrapper = new ResponseWrapper(200, obj).success();
            return new ResponseEntity<>(wrapper, HttpStatus.OK);
        } catch (AppError e) {
            ResponseWrapper wrapper = new ResponseWrapper(e.code, e.getMessage());
            return new ResponseEntity<>(wrapper, e.httpStatus);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseWrapper> delete(@Valid @RequestBody DeleteRequest deleteRequest, BindingResult bindingResult) {
        ResponseEntity<ResponseWrapper> errorValidation = GlobalExceptionHandler.HandleValidationError(bindingResult);
        if (errorValidation != null) {
            return errorValidation;
        }
        try {
            employeeServiceImpl.delete(deleteRequest);
            ResponseWrapper wrapper = new ResponseWrapper(200, "Sukses").success();
            return new ResponseEntity<>(wrapper, HttpStatus.OK);
        } catch (AppError e) {
            ResponseWrapper wrapper = new ResponseWrapper(e.code, e.getMessage());
            return new ResponseEntity<>(wrapper, e.httpStatus);
        }
    }
}
