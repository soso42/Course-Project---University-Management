package org.example.services;

import org.example.beans.Student;
import org.example.exceptions.StudentNotFoundException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UniManagementImplTest {

    private final UniManagementImpl uniManagement = new UniManagementImpl();


    @Test
    void testCreateStudent() throws NoSuchFieldException, IllegalAccessException {
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
