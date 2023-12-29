package com.idstar.employeeApp.service.Impl;

import com.idstar.employeeApp.dto.ErrorWrapper.AppError;
import com.idstar.employeeApp.dto.Employee.CreateEmployeeRequest;
import com.idstar.employeeApp.dto.DeleteRequest;
import com.idstar.employeeApp.dto.Employee.UpdateEmployeeRequest;
import com.idstar.employeeApp.model.Employee;
import com.idstar.employeeApp.model.EmployeeDetail;
import com.idstar.employeeApp.repository.EmployeeDetailRepository;
import com.idstar.employeeApp.repository.EmployeeRepository;
import com.idstar.employeeApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDetailRepository employeeDetailRepository;

    @Override
    public Optional<Employee> get(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Page<Employee> getAll(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return employeeRepository.findAll(paging);
    }

    @Override
    public Employee create(CreateEmployeeRequest employee) throws AppError {
        Date dobDate = null;
        try {
            dobDate = new SimpleDateFormat("yyyy-MM-dd").parse(employee.dob);
        } catch (ParseException e) {
            throw new AppError(HttpStatus.BAD_REQUEST, "Invalid dob format");
        }

        EmployeeDetail newEmployeeDetail = new EmployeeDetail().Generate(employee.createEmployeeDetailRequest.nik, employee.createEmployeeDetailRequest.npwp);
        Employee newEmployee = new Employee().Generate(employee.name, dobDate, employee.address, employee.status, newEmployeeDetail);
        return employeeRepository.save(newEmployee);
    }

    @Override
    public Employee update(UpdateEmployeeRequest employee) throws AppError {
        Optional<Employee> employeeExists = employeeRepository.findById(employee.id);
        if (employeeExists.isEmpty()) {
            throw new AppError(HttpStatus.NOT_FOUND, "Employee Id Not Found");
        }
        if (!Objects.equals(employeeExists.get().getId(), employee.updateEmployeeDetailRequest.id)) {
            throw new AppError(HttpStatus.NOT_FOUND, "Can't update using other employee detail id");
        }

        boolean employeeDetailIsExist = employeeDetailRepository.existsById(employee.updateEmployeeDetailRequest.id);
        if (!employeeDetailIsExist) {
            throw new AppError(HttpStatus.NOT_FOUND, "Employee Detail Id Not Found");
        }

        Date dobDate = null;
        try {
            dobDate = new SimpleDateFormat("yyyy-MM-dd").parse(employee.dob);
        } catch (ParseException e) {
            throw new AppError(HttpStatus.BAD_REQUEST, "Invalid dob format");
        }

        EmployeeDetail newEmployeeDetail = new EmployeeDetail().Modify(employee.updateEmployeeDetailRequest.id, employee.updateEmployeeDetailRequest.nik, employee.updateEmployeeDetailRequest.npwp);
        Employee newEmployee = new Employee().Modify(employee.id, employee.name, dobDate, employee.address, employee.status, newEmployeeDetail);
        return employeeRepository.save(newEmployee);
    }

    @Override
    public void delete(DeleteRequest employee) throws AppError {
        Employee deleteEmployee = new Employee();
        deleteEmployee.setId(employee.id);

        Optional<Employee> toBeDeletedEmployee = employeeRepository.findById(employee.id);
        if (toBeDeletedEmployee.isEmpty()) {
            throw new AppError(HttpStatus.NOT_FOUND, "Employee Id Not Found");
        }
        EmployeeDetail deleteEmployeeDetail = new EmployeeDetail();
        deleteEmployeeDetail.setId(toBeDeletedEmployee.get().getEmployeeDetail().getId());

        employeeRepository.delete(deleteEmployee);
        employeeDetailRepository.deleteById(deleteEmployeeDetail.getId());
    }
}
