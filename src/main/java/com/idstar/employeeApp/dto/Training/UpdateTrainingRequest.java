package com.idstar.employeeApp.dto.Training;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class UpdateTrainingRequest {
    @JsonProperty("id")
    @NotNull(message = "Please provide training id")
    public Long id;

    @NotNull(message = "Please provide instructor")
    @JsonProperty("pengajar")
    public String instructor;

    @NotNull(message = "Please provide title")
    @JsonProperty("tema")
    public String title;
}
