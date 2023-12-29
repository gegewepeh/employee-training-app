package com.idstar.employeeApp.service.Impl;

import com.idstar.employeeApp.dto.DeleteRequest;
import com.idstar.employeeApp.dto.EmployeeTraining.CreateEmployeeTrainingRequest;
import com.idstar.employeeApp.dto.EmployeeTraining.UpdateEmployeeTrainingRequest;
import com.idstar.employeeApp.dto.ErrorWrapper.AppError;
import com.idstar.employeeApp.dto.Training.CreateTrainingRequest;
import com.idstar.employeeApp.dto.Training.UpdateTrainingRequest;
import com.idstar.employeeApp.model.Employee;
import com.idstar.employeeApp.model.EmployeeTraining;
import com.idstar.employeeApp.model.Training;
import com.idstar.employeeApp.repository.EmployeeRepository;
import com.idstar.employeeApp.repository.EmployeeTrainingRepository;
import com.idstar.employeeApp.repository.TrainingRepository;
import com.idstar.employeeApp.service.EmployeeTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class EmployeeTrainingServiceImpl implements EmployeeTrainingService {
    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public TrainingRepository trainingRepository;

    @Autowired
    public EmployeeTrainingRepository employeeTrainingRepository;

    @Override
    public Optional<EmployeeTraining> get(long id) {
        return employeeTrainingRepository.findById(id);
    }

    @Override
    public Page<EmployeeTraining> getAll(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return employeeTrainingRepository.findAll(paging);
    }

    @Override
    public EmployeeTraining create(CreateEmployeeTrainingRequest req) throws AppError {
        Date formattedDate = null;
        try {
            formattedDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.date);
        } catch (ParseException e) {
            throw new AppError(HttpStatus.BAD_REQUEST, "Invalid date format");
        }

        Optional<Employee> employee = employeeRepository.findById(req.employee.id);
        if (employee.isEmpty()) {
            throw new AppError(HttpStatus.NOT_FOUND, "Employee id not found");
        }
        Optional<Training> training = trainingRepository.findById(req.training.id);
        if (training.isEmpty()) {
            throw new AppError(HttpStatus.NOT_FOUND, "Training id not found");
        }
        EmployeeTraining employeeTraining = new EmployeeTraining().Generate(formattedDate, employee.get(), training.get());

        return employeeTrainingRepository.save((employeeTraining));
    }

    @Override
    public EmployeeTraining update(UpdateEmployeeTrainingRequest req) throws AppError {
        Date formattedDate = null;
        try {
            formattedDate = new SimpleDateFormat("yyyy-MM-dd").parse(req.date);
        } catch (ParseException e) {
            throw new AppError(HttpStatus.BAD_REQUEST, "Invalid date format");
        }

        Optional<Employee> employee = employeeRepository.findById(req.employee.id);
        if (employee.isEmpty())
            throw new AppError(HttpStatus.NOT_FOUND, "Employee id not found");

        Optional<Training> training = trainingRepository.findById(req.training.id);
        if (training.isEmpty())
            throw new AppError(HttpStatus.NOT_FOUND, "Training id not found");

        EmployeeTraining employeeTraining = new EmployeeTraining().Modify(req.id, formattedDate, employee.get(), training.get());
        return employeeTrainingRepository.save(employeeTraining);
    }

    @Override
    public void delete(DeleteRequest req) throws AppError {
        Optional<EmployeeTraining> toBeDeletedEmployeeTraining = employeeTrainingRepository.findById(req.id);
        if (toBeDeletedEmployeeTraining.isEmpty()) {
            throw new AppError(HttpStatus.NOT_FOUND, "EmployeeTraining id Not Found");
        }

        employeeTrainingRepository.deleteById(req.id);
    }
}
