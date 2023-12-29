package com.idstar.employeeApp.dto.EmployeeTraining;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class TrainingReq {
    @JsonProperty("id")
    @NotNull(message = "Please provide a training id")
    public Long id;
}
