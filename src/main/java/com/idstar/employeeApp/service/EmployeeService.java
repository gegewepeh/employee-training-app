package com.idstar.employeeApp.service;

import com.idstar.employeeApp.dto.ErrorWrapper.AppError;
import com.idstar.employeeApp.dto.Employee.CreateEmployeeRequest;
import com.idstar.employeeApp.dto.DeleteRequest;
import com.idstar.employeeApp.dto.Employee.UpdateEmployeeRequest;
import com.idstar.employeeApp.model.Employee;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> get(long id);

    Page<Employee> getAll(int page, int size);

    Employee create(CreateEmployeeRequest createEmployeeRequest) throws AppError;

    Employee update(UpdateEmployeeRequest updateEmployeeRequest) throws AppError;

    void delete(DeleteRequest deleteRequest) throws AppError;
}
