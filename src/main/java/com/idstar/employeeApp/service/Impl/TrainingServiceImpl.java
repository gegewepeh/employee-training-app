package com.idstar.employeeApp.service.Impl;

import com.idstar.employeeApp.dto.DeleteRequest;
import com.idstar.employeeApp.dto.ErrorWrapper.AppError;
import com.idstar.employeeApp.dto.Training.CreateTrainingRequest;
import com.idstar.employeeApp.dto.Training.UpdateTrainingRequest;
import com.idstar.employeeApp.model.BankAccount;
import com.idstar.employeeApp.model.Training;
import com.idstar.employeeApp.repository.TrainingRepository;
import com.idstar.employeeApp.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {
    @Autowired
    public TrainingRepository trainingRepository;

    @Override
    public Optional<Training> get(long id) {
        return trainingRepository.findById(id);
    }

    @Override
    public Page<Training> getAll(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return trainingRepository.findAll(paging);
    }

    @Override
    public Training create(CreateTrainingRequest req) throws AppError {
        Training training = new Training().Generate(req.instructor, req.title);

        return trainingRepository.save(training);
    }

    @Override
    public Training update(UpdateTrainingRequest req) throws AppError {
        boolean trainingExists = trainingRepository.existsById(req.id);
        if (!trainingExists)
            throw new AppError(HttpStatus.NOT_FOUND, "Training id doesn't exist");

        Training trainingEdit = new Training().Modify(req.id, req.instructor, req.title);
        return trainingRepository.save(trainingEdit);
    }

    @Override
    public void delete(DeleteRequest req) throws AppError {
        Optional<Training> toBeDeletedTraining = trainingRepository.findById(req.id);
        if (toBeDeletedTraining.isEmpty()) {
            throw new AppError(HttpStatus.NOT_FOUND, "Training id Not Found");
        }

        trainingRepository.deleteById(req.id);
    }
}
