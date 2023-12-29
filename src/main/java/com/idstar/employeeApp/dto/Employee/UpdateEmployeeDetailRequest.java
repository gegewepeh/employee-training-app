package com.idstar.employeeApp.dto.Employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class UpdateEmployeeDetailRequest {
    @JsonProperty("id")
    @NotNull(message = "Please provide an id")
    public Long id;

    @NotNull(message = "Please provide NIK")
    public String nik;

    @NotNull(message = "Please provide NPWP")
    public String npwp;
}
