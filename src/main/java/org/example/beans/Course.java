package org.example.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class Course {

    public static final int MAX_STUDENTS = 30;

    private Long id;
    @Getter
    private String name;
    @Getter
    private List<Student> students;
    @Setter
    private Lector assistant;
    @Setter
    private Lector lector;


    public Course(String name) {
        this.name = name;
    }

    public Course(String name, Lector lector, Lector assistant) {
        this.name = name;
        this.lector = lector;
        this.assistant = assistant;
    }


    public boolean addStudent(Student student) {
        if (students.size() < MAX_STUDENTS) {
            students.add(student);
            return true;
        }
        return false;
    }

    public void deleteStudent(Student student) {
        this.students.remove(student);
    }

}
