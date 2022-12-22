package org.example.services;

import org.example.beans.Course;
import org.example.beans.Student;
import org.example.exceptions.CourseNotFoundException;
import org.example.exceptions.StudentNotFoundException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UniManagementImplTest {

    private final UniManagementImpl uniManagement = new UniManagementImpl();


    @Test
    void createCourse_HappyPath() throws NoSuchFieldException, IllegalAccessException {
        // Given
        Field field = uniManagement.getClass().getDeclaredField("courses");
        field.setAccessible(true);
        // When
        uniManagement.createCourse("Java");
        List<Course> courses = (List<Course>) field.get(uniManagement);
        // Then
        assertEquals(1, courses.size());
    }

    @Test
    void createCourse_exception() {
        // Given
        uniManagement.createCourse("Java");
        // When
        // Then
        assertThrows(RuntimeException.class, () -> {
            uniManagement.createCourse("Java");
        });
    }

    @Test
    void deleteCourse_happyPath() throws NoSuchFieldException, CourseNotFoundException, IllegalAccessException {
        // Given
        Field field = uniManagement.getClass().getDeclaredField("courses");
        field.setAccessible(true);
        uniManagement.createCourse("Java");
        // When
        uniManagement.deleteCourse("Java");
        List<Course> courses = (List<Course>) field.get(uniManagement);
        // Given
        assertEquals(0, courses.size());
    }

    @Test
    void deleteCourse_exception() {
        // Given
        // When
        // Then
        assertThrows(CourseNotFoundException.class, () -> {
            uniManagement.deleteCourse("Java");
        });
    }

    @Test
    void testCreateStudent_happyPath() throws NoSuchFieldException, IllegalAccessException {
        // Given
        Field field = uniManagement.getClass().getDeclaredField("lastUsedStudentIndex");
        field.setAccessible(true);
        int initialNumOfStudents = field.getInt(uniManagement);
        // When
        uniManagement.createStudent(1, "John", "Doe", "CS101");
        int finalNumOfStudents = field.getInt(uniManagement);
        // Then
        assertEquals(initialNumOfStudents + 1, finalNumOfStudents);
    }

    @Test
    void testCreateStudent_exception() throws NoSuchFieldException, IllegalAccessException {
        // Given
        uniManagement.createStudent(1, "John", "Doe", "CS101");
        // When
        // Then
        assertThrows(RuntimeException.class, () -> {
            uniManagement.createStudent(1, "John", "Doe", "CS101");
        });
    }

    @Test
    void testDeleteStudent_happyPath() throws NoSuchFieldException, IllegalAccessException, StudentNotFoundException {
        // Given
        Field field = uniManagement.getClass().getDeclaredField("students");
        field.setAccessible(true);
        uniManagement.createStudent(1, "John", "Doe", "CS101");
        uniManagement.createStudent(2, "Jane", "Doe", "CS102B");
        uniManagement.createStudent(3, "John", "Smith", "CS102C");
        // When
        uniManagement.deleteStudent(2);
        // Then
        Student[] students = (Student[]) field.get(uniManagement);
        int total = 0;
        for (Student s: students) {
            if (s != null) total++;
        }
        assertEquals(2, total);
        Arrays.stream(students).forEach(s -> {
            if (s != null) {
                System.out.println(s.getId() + " " + s.getFirstName() + " " + s.getLastName());
            }
        });
    }

    @Test
    void testDeleteStudent_Exception() throws NoSuchFieldException {
        // Given
        Field field = uniManagement.getClass().getDeclaredField("students");
        field.setAccessible(true);
        // When
        // Then
        assertThrows(StudentNotFoundException.class, () -> {
            uniManagement.deleteStudent(2);
        });
    }


}
