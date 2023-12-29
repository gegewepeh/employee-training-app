package com.idstar.employeeApp.dto.BankAccount;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.idstar.employeeApp.dto.Employee.CreateEmployeeDetailRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class CreateBankAccountRequest {
    @JsonProperty("nama")
    @NotNull(message = "Please provide a name")
    public String name;

    @JsonProperty("jenis")
    @NotNull(message = "Please provide a date of birth")
    public String type;

    @JsonProperty("rekening")
    @NotNull(message = "Please provide a date of birth")
    public String account;

    @JsonProperty("alamat")
    @NotNull(message = "Please provide a status")
    public String address;

    @JsonProperty("karyawan")
    @Valid
    public EmployeeRequest employeeRequest;
}
