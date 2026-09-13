package com.example.lab7.Service;

import com.example.lab7.Model.Course;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses = new ArrayList<>();

    public ArrayList<Course> getCourses(){
        return courses;
    }

    //add a course but make sure that id and title are unique
    public boolean addCourse(Course course){
        if(course.getRegisteredStudents() > course.getCapacity()){
            return false;
        }
        for(Course course1: courses){
            if(course1.getId().equals(course.getId())){
                return false;
            }
            if(course1.getTitle().equalsIgnoreCase(course.getTitle())){
                return false;
            }
        }
        courses.add(course);
        return true;
    }


    //in update make sure that the title is unique and if its repeated return false
    public boolean updateCourse(String id, Course updateCourse){
        if(updateCourse.getRegisteredStudents() > updateCourse.getCapacity()){
            return false;
        }
        for(int i=0; i<courses.size(); i++){
            if(courses.get(i).getId().equals(id)){
                for(Course course: courses){
                    if(!course.getId().equals(id) && course.getTitle().equalsIgnoreCase(updateCourse.getTitle())){
                        return false;
                    }
                }
                updateCourse.setId(id);
                courses.set(i,updateCourse);
                return true;
            }

        }
        return false;
    }

    public boolean deleteCourse(String id){
        for(int i=0; i<courses.size(); i++){
            if(courses.get(i).getId().equals(id)){
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Course> searchByLevel( String level ){
        ArrayList<Course> levels = new ArrayList<>();
        for(Course course: courses){
            if(course.getLevel().equalsIgnoreCase(level)){
                levels.add(course);
            }
        }
        return levels;
    }


    //if number of registered students is greater than the capacity of the course, then registration failed.
    public boolean register(String id){
        for(Course course : courses){
            if(course.getId().equals(id)){
                if (course.getRegisteredStudents() >= course.getCapacity()) {
                    return false;
                }
                course.setRegisteredStudents(course.getRegisteredStudents() + 1);
                return true;
            }
        }
        return false;
    }



}
