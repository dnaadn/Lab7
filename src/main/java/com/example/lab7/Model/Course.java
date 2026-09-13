package com.example.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "Course Id is required")
    private String id;

    @NotEmpty(message = "Course title is required")
    private String title;

    @NotEmpty(message = "Course description can't be empty")
    private String description;


    @NotEmpty(message = "Course level is required")
    @Pattern(regexp = "(?i)^(Beginner|Intermediate|Advanced)$")
    private String level;

    @NotNull(message = "Course Capacity is required")
    @Min(value = 5, message = "Capacity must be at least 5")
    @Max(value = 100, message = "Capacity cannot exceed 100")
    private Integer capacity;

    @PositiveOrZero
    @NotNull
    private Integer registeredStudents;



}
