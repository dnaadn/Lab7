package com.example.lab7.Service;

import com.example.lab7.Model.Assignment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class AssignmentService {

    ArrayList<Assignment> assignments = new ArrayList<>();

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }


    //id is unique
    public boolean addAssignment(Assignment assignment) {
        for (Assignment assignment1 : assignments) {
            if (assignment1.getId().equals(assignment.getId())) {
                return false;
            }
        }
        assignments.add(assignment);
        return true;
    }

    public boolean updateAssignment(String id, Assignment updatedAssignment) {
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getId().equals(id)) {
                updatedAssignment.setId(id);
                assignments.set(i, updatedAssignment);
                return true;
            }
        }
        return false;
    }


    public boolean deleteAssignment(String id) {
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getId().equals(id)) {
                assignments.remove(i);
                return true;
            }
        }
        return false;
    }


    public boolean submitAssignment(String id) {
        for (Assignment assignment : assignments) {
            if (assignment.getId().equals(id)) {

                // Already submitted > false
                if (assignment.getStatus().equalsIgnoreCase("submitted")) {
                    return false;
                }
                // Deadline has passed > false
                if (LocalDate.now().isAfter(assignment.getDueDate())) {
                    return false;
                }

                // Submit assignment
                assignment.setStatus("Submitted");
                assignment.setSubmissionDate(LocalDate.now());
                return true;
            }
        }
        return false;
    }


    public ArrayList<Assignment> searchByStatus(String status) {
        ArrayList<Assignment> result = new ArrayList<>();

        for (Assignment assignment : assignments) {
            if (assignment.getStatus().equalsIgnoreCase(status)) {
                result.add(assignment);
            }
        }
        return result;
    }

}
