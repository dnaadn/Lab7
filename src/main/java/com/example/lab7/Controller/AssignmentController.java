package com.example.lab7.Controller;

import com.example.lab7.API.ApiResponse;
import com.example.lab7.Model.Assignment;
import com.example.lab7.Service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/assignment")
@RequiredArgsConstructor
public class AssignmentController {


    private final AssignmentService assignmentService;


    @GetMapping("/get")
    public ResponseEntity<?> getAssignments(){
        ArrayList<Assignment> assignments = assignmentService.getAssignments();
        return ResponseEntity.status(200).body(assignments);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAssignment(@RequestBody @Valid Assignment assignment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isAdded = assignmentService.addAssignment(assignment);
        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Assignment ID already exists"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAssignment(@PathVariable String id, @RequestBody @Valid Assignment updatedAssignment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = assignmentService.updateAssignment(id, updatedAssignment);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Assignment not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable String id) {

        boolean isDeleted = assignmentService.deleteAssignment(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Assignment not found"));
    }

    @PutMapping("/submit/{id}")
    public ResponseEntity<?> submitAssignment(@PathVariable String id) {

        boolean isSubmitted = assignmentService.submitAssignment(id);
        if (isSubmitted) {
            return ResponseEntity.status(200).body(new ApiResponse("Assignment submitted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Assignment cannot be submitted"));
    }

    @GetMapping("/search/{status}")
    public ResponseEntity<?> searchByStatus(@PathVariable String status) {

        ArrayList<Assignment> result = assignmentService.searchByStatus(status);

        if (!result.isEmpty()) {
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(404).body(new ApiResponse("No assignments found with this status"));
    }

}

