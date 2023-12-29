package com.idstar.employeeApp.dto.Employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateEmployeeRequest {
    @JsonProperty("nama")
    @NotNull(message = "Please provide a name")
    public String name;

    @JsonProperty("dob")
    @NotNull(message = "Please provide a date of birth")
    public String dob;

    @JsonProperty("status")
    @NotNull(message = "Please provide a status")
    public String status;

    @JsonProperty("alamat")
    @NotNull(message = "Please provide an address")
    public String address;

    @JsonProperty("detailKaryawan")
    @Valid
    public CreateEmployeeDetailRequest createEmployeeDetailRequest;
}

