<img width="776" height="402" alt="UML" src="https://github.com/user-attachments/assets/bbb8db60-51bf-45b2-a5d0-fc97d968b0bc" />


## About the Project

This is a simple Learning Management System (LMS) REST API built with Spring Boot, developed as a lab exercise to practice a layered architecture (Model → Service → Controller).

The system manages two core resources:

- Course — represents a course with an id, title, description, level (Beginner/Intermediate/Advanced), capacity, and number of registered students.
- Assignment — represents an assignment tied to a course via courseId, with a due date, submission date, and status (Not Started / In Progress / Submitted).

**Key features:**

- Full CRUD operations for both Course and Assignment
- Student registration for a course, with capacity enforcement
- Assignment submission logic that blocks re-submission and rejects late submissions past the due date
- Search endpoints — courses by level, assignments by status
- Request validation using Jakarta Bean Validation (@NotBlank, @Min/@Max, @Pattern, etc.), with custom error messages returned on invalid input
- Lombok (@Data, @AllArgsConstructor) to reduce boilerplate in the model classes
