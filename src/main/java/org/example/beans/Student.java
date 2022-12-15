package org.example.beans;

import java.util.List;

public class Student extends User {

    public static final int MAX_COURSES = 10;

    private Long facNumber;
    private List<Course> courses;


    public Student() {}

    public Student(Long id, String firstName, String lastName, Long facNumber) {
        super(id, firstName, lastName);
        this.facNumber = facNumber;
    }


    public void addCourse(Course course) {
        if (courses.size() < MAX_COURSES) {
            courses.add(course);
        }
    }

    public void deleteCourse(Course course) {
        courses.remove(course);
    }

}
