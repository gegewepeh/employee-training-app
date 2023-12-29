package com.idstar.employeeApp.service;

import com.idstar.employeeApp.dto.DeleteRequest;
import com.idstar.employeeApp.dto.EmployeeTraining.CreateEmployeeTrainingRequest;
import com.idstar.employeeApp.dto.EmployeeTraining.UpdateEmployeeTrainingRequest;
import com.idstar.employeeApp.dto.ErrorWrapper.AppError;
import com.idstar.employeeApp.dto.Training.CreateTrainingRequest;
import com.idstar.employeeApp.dto.Training.UpdateTrainingRequest;
import com.idstar.employeeApp.model.EmployeeTraining;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface EmployeeTrainingService {
    Optional<EmployeeTraining> get(long id);

    Page<EmployeeTraining> getAll(int page, int size);

    EmployeeTraining create(CreateEmployeeTrainingRequest createEmployeeTrainingRequest) throws AppError;

    EmployeeTraining update(UpdateEmployeeTrainingRequest updateEmployeeTrainingRequest) throws AppError;

    void delete(DeleteRequest deleteRequest) throws AppError;
}
