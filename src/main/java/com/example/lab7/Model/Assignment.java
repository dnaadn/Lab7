package com.example.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Assignment {

    @NotEmpty(message = "Assignment Id is required")
    private String id;

    @NotBlank(message = "Assignment title is required")
    private String title;

    @NotBlank(message = "Assignment description is required")
    private String description;

    @NotNull(message = "Due date is required")
    @FutureOrPresent
    private LocalDate dueDate;

    @NotBlank(message = "Course Id is required")
    private String courseId;

    @Pattern(regexp = "(?i)^(not started|in progress|submitted)$", message = "Status must be Not Started, In Progress, or Submitted")
    @NotEmpty(message = "Status is required")
    private String status;

    private LocalDate submissionDate;

}
