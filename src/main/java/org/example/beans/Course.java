package org.example.beans;

import lombok.Setter;

import java.util.List;


public class Course {

    private Long id;
    private String name;
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


    public void addStudent(Student student) {
        if (students.size() < 30) {
            students.add(student);
        }
    }

    public void deleteStudent(Student student) {
        this.students.remove(student);
    }

}
