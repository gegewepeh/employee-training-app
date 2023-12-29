package com.idstar.employeeApp.dto.BankAccount;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class EmployeeRequest {
    @JsonProperty("id")
    @NotNull(message = "Please provide employee id")
    public Long id;
}
