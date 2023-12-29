package com.idstar.employeeApp.service;

import com.idstar.employeeApp.dto.DeleteRequest;
import com.idstar.employeeApp.dto.ErrorWrapper.AppError;
import com.idstar.employeeApp.dto.Training.CreateTrainingRequest;
import com.idstar.employeeApp.dto.Training.UpdateTrainingRequest;
import com.idstar.employeeApp.model.Training;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface TrainingService {
    Optional<Training> get(long id);

    Page<Training> getAll(int page, int size);

    Training create(CreateTrainingRequest createTrainingRequest) throws AppError;

    Training update(UpdateTrainingRequest updateTrainingRequest) throws AppError;

    void delete(DeleteRequest deleteRequest) throws AppError;
}
