package com.idstar.employeeApp.controller;

import com.idstar.employeeApp.dto.BankAccount.CreateBankAccountRequest;
import com.idstar.employeeApp.dto.BankAccount.UpdateBankAccountRequest;
import com.idstar.employeeApp.dto.DeleteRequest;
import com.idstar.employeeApp.dto.ErrorWrapper.AppError;
import com.idstar.employeeApp.dto.ResponseWrapper;
import com.idstar.employeeApp.model.BankAccount;
import com.idstar.employeeApp.service.Impl.BankAccountServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/idstar/rekening")
public class BankAccountController {
    @Autowired
    public BankAccountServiceImpl bankAccountServiceImpl;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResponseWrapper> getById(@PathVariable long id) {
        Optional<BankAccount> employee = bankAccountServiceImpl.get(id);
        ResponseWrapper wrapper = new ResponseWrapper(200, employee).success();
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<ResponseWrapper> getList(@RequestParam int page, @RequestParam int size) {
        Page<BankAccount> obj = bankAccountServiceImpl.getAll(page, size);
        ResponseWrapper wrapper = new ResponseWrapper(200, obj).success();
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseWrapper> create(@Valid @RequestBody CreateBankAccountRequest reqObject, BindingResult bindingResult) {
        ResponseEntity<ResponseWrapper> errorValidation = GlobalExceptionHandler.HandleValidationError(bindingResult);
        if (errorValidation != null) {
            return errorValidation;
        }
        try {
            BankAccount obj = bankAccountServiceImpl.create(reqObject);
            ResponseWrapper wrapper = new ResponseWrapper(200, obj).success();
            return new ResponseEntity<>(wrapper, HttpStatus.OK);
        } catch (AppError e) {
            ResponseWrapper wrapper = new ResponseWrapper(e.code, e.getMessage());
            return new ResponseEntity<>(wrapper, e.httpStatus);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseWrapper> update(@Valid @RequestBody UpdateBankAccountRequest reqObject, BindingResult bindingResult) {
        ResponseEntity<ResponseWrapper> errorValidation = GlobalExceptionHandler.HandleValidationError(bindingResult);
        if (errorValidation != null) {
            return errorValidation;
        }
        try {
            BankAccount obj = bankAccountServiceImpl.update(reqObject);
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
            bankAccountServiceImpl.delete(deleteRequest);
            ResponseWrapper wrapper = new ResponseWrapper(200, "Sukses").success();
            return new ResponseEntity<>(wrapper, HttpStatus.OK);
        } catch (AppError e) {
            ResponseWrapper wrapper = new ResponseWrapper(e.code, e.getMessage());
            return new ResponseEntity<>(wrapper, e.httpStatus);
        }
    }
}
