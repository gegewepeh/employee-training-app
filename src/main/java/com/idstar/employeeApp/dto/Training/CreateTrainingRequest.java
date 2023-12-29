package com.idstar.employeeApp.dto.Training;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class CreateTrainingRequest {
    @NotNull(message = "Please provide instructor")
    @JsonProperty("pengajar")
    public String instructor;

    @NotNull(message = "Please provide title")
    @JsonProperty("tema")
    public String title;
}
