package com.example.lab6q1.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EmployeeModel {


    @NotEmpty(message = "the Id should not Empty")
    @Size(min = 2 , message = "ID must be more than 2 characters")
    private String ID ;


    @NotEmpty(message = "the name should not Empty")
    @Size(min = 4 , message = "ID must be more than 4 characters")

    private String name ;


    @Email(message = "please enter a valid email")
    private String email ;


    @Pattern(regexp = "^05?[0-9]{10}+$")
    private Integer phoneNumber ;


    @NotNull(message = "the age should not Empty")
    @Pattern(regexp = "[0-9]+" , message = "Must be a number")
    @Min(value = 25 , message = "your age must be more than 25")
    private Integer age ;


    @NotEmpty(message = "the position should not Empty")
    @Pattern(regexp = "^(supervisor|coordinator)$" , message = "Must be either \"supervisor\" or \"coordinator\" only")
    private String position ;


    private Boolean onLeave ;


    @NotEmpty(message = "the hireDate should not Empty")
    @PastOrPresent(message = "Must be a valid year (e.g., 1900 or later).")
    @Max(1900)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate ;


    @NotNull(message = "the AnnualLeave should not Empty")
    @Positive(message = "Must be a positive number")
    private Integer AnnualLeave ;


}
