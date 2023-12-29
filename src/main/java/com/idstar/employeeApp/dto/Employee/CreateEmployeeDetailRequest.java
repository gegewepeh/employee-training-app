package com.idstar.employeeApp.dto.Employee;


import jakarta.validation.constraints.NotNull;

public class CreateEmployeeDetailRequest {
    @NotNull(message = "Please provide NIK")
    public String nik;

    @NotNull(message = "Please provide NPWP")
    public String npwp;
}
