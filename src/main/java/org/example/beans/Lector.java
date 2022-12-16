package org.example.beans;

import java.util.List;


public class Lector extends User {

    public static final int MAX_COURSES = 4;

    private LectorType lectorType;
    private List<Course> courses;


    public Lector() {}

    public Lector(int id, String firstName, String lastName, LectorType lectorType) {
        super(id, firstName, lastName);
        this.lectorType = lectorType;
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
