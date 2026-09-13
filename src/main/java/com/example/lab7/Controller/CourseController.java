package com.example.lab7.Controller;

import com.example.lab7.API.ApiResponse;
import com.example.lab7.Model.Course;
import com.example.lab7.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;


    @GetMapping("/get")
    public ResponseEntity<?> getCourses(){
        ArrayList<Course> courses = courseService.getCourses();
        return ResponseEntity.status(200).body(courses);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isAdded = courseService.addCourse(course);
        if(isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("Course added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course ID/title already exists or registered students exceed capacity"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @RequestBody @Valid Course updatedCourse, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = courseService.updateCourse(id,updatedCourse);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course not found, title already exists, or registered students exceed capacity"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id){
        boolean isDeleted = courseService.deleteCourse(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
    }



    @GetMapping("/search/{level}")
    public ResponseEntity<?> searchByLevel(@PathVariable String level){
        ArrayList<Course> result = courseService.searchByLevel(level);

        if(!result.isEmpty()){
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
    }


    @PutMapping("/register/{id}")
    public ResponseEntity<?> register(@PathVariable String id){
        boolean isRegistered = courseService.register(id);
        if(isRegistered){
            return ResponseEntity.status(200).body(new ApiResponse("Student registered successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course capacity is full"));

    }
}
