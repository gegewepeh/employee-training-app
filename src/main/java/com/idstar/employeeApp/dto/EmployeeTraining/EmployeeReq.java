package com.idstar.employeeApp.dto.EmployeeTraining;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class EmployeeReq {
    @JsonProperty("id")
    @NotNull(message = "Please provide an employee id")
    public Long id;
}
