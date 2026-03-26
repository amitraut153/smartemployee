package com.smart.smartemployee.entity;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "1000.0", inclusive = false, message = "Salary must be greater than 1000")
    private Double salary;

    @NotNull(message = "Joining date required")
    private LocalDate joiningDate;
}