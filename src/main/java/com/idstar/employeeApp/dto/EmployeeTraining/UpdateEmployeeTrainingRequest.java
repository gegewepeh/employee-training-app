package com.idstar.employeeApp.dto.EmployeeTraining;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class UpdateEmployeeTrainingRequest {
    @JsonProperty("id")
    @NotNull(message = "Please provide employee-training id")
    public Long id;

    @NotNull(message = "Please provide date")
    @JsonProperty("tanggal")
    public String date;

    @JsonProperty("karyawan")
    @Valid
    public EmployeeReq employee;

    @JsonProperty("training")
    @Valid
    public TrainingReq training;
}
