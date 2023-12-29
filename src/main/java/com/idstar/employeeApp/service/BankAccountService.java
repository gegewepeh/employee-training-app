package com.idstar.employeeApp.service;

import com.idstar.employeeApp.dto.BankAccount.CreateBankAccountRequest;
import com.idstar.employeeApp.dto.BankAccount.UpdateBankAccountRequest;
import com.idstar.employeeApp.dto.DeleteRequest;
import com.idstar.employeeApp.dto.ErrorWrapper.AppError;
import com.idstar.employeeApp.model.BankAccount;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BankAccountService {
    Optional<BankAccount> get(long id);

    Page<BankAccount> getAll(int page, int size);

    BankAccount create(CreateBankAccountRequest createBankAccountRequest) throws AppError;

    BankAccount update(UpdateBankAccountRequest updateBankAccountRequest) throws AppError;

    void delete(DeleteRequest deleteRequest) throws AppError;
}
