package org.example.beans;

import lombok.Getter;

import java.util.List;

public class Student extends User {

    public static final int MAX_COURSES = 10;

    private String facNumber;
    @Getter
    private List<Course> courses;


    public Student() {}

    public Student(int id, String firstName, String lastName, String facNumber) {
        super(id, firstName, lastName);
        this.facNumber = facNumber;
    }


    public boolean addCourse(Course course) {
        if (courses.size() < MAX_COURSES) {
            courses.add(course);
            return true;
        }
        return false;
    }

    public void deleteCourse(Course course) {
        courses.remove(course);
    }

}
