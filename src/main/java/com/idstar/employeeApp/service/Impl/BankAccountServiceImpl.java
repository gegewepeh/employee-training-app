package com.idstar.employeeApp.service.Impl;

import com.idstar.employeeApp.dto.BankAccount.CreateBankAccountRequest;
import com.idstar.employeeApp.dto.BankAccount.UpdateBankAccountRequest;
import com.idstar.employeeApp.dto.DeleteRequest;
import com.idstar.employeeApp.dto.ErrorWrapper.AppError;
import com.idstar.employeeApp.model.BankAccount;
import com.idstar.employeeApp.model.Employee;
import com.idstar.employeeApp.repository.BankAccountRepository;
import com.idstar.employeeApp.repository.EmployeeRepository;
import com.idstar.employeeApp.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    public BankAccountRepository bankAccountRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Override
    public Optional<BankAccount> get(long id) {
        return bankAccountRepository.findById(id);
    }

    @Override
    public Page<BankAccount> getAll(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return bankAccountRepository.findAll(paging);
    }

    @Override
    @Transactional
    public BankAccount create(CreateBankAccountRequest req) throws AppError {
        Optional<Employee> employeeExist = employeeRepository.findById(req.employeeRequest.id);
        if (employeeExist.isEmpty()) {
            throw new AppError(HttpStatus.NOT_FOUND, "Employee Id Not Found");
        }

        Employee employeeEdit = employeeExist.get();
        employeeEdit.setAddress(req.address);
        employeeRepository.save(employeeEdit);

        BankAccount bankAccount = new BankAccount().Generate(req.name, req.type, req.account, employeeEdit);

        return bankAccountRepository.save(bankAccount);
    }

    @Override
    @Transactional
    public BankAccount update(UpdateBankAccountRequest req) throws AppError {
        boolean bankAccountExists = bankAccountRepository.existsById(req.id);
        if (!bankAccountExists)
            throw new AppError(HttpStatus.NOT_FOUND, "Bank account id doesn't exist");

        Optional<Employee> employeeExist = employeeRepository.findById(req.employeeRequest.id);
        if (employeeExist.isEmpty()) {
            throw new AppError(HttpStatus.NOT_FOUND, "Employee Id Not Found");
        }
        Employee employeeEdit = employeeExist.get();
        employeeEdit.setAddress(req.address);
        employeeRepository.save(employeeEdit);

        BankAccount editBankAccount = new BankAccount().Modify(req.id, req.name, req.type, req.account, employeeExist.get());
        return bankAccountRepository.save(editBankAccount);
    }

    @Override
    public void delete(DeleteRequest req) throws AppError {
        Optional<BankAccount> toBeDeletedBankAccount = bankAccountRepository.findById(req.id);
        if (toBeDeletedBankAccount.isEmpty()) {
            throw new AppError(HttpStatus.NOT_FOUND, "Bank account id Not Found");
        }

        bankAccountRepository.deleteById(req.id);
    }
}
