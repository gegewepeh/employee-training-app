package com.idstar.employeeApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class DeleteRequest {
    @JsonProperty("id")
    @NotNull(message = "Please provide an id")
    public Long id;
}
