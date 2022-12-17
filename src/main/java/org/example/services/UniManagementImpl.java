package org.example.services;

import org.example.beans.Course;
import org.example.beans.Lector;
import org.example.beans.LectorType;
import org.example.beans.Student;
import org.example.exceptions.CourseNotFoundException;
import org.example.exceptions.StudentNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class UniManagementImpl implements UniManagement {

    public static final int NUM_OF_STUDENTS = 5;

    private Student[] students = new Student[NUM_OF_STUDENTS];
    private int lastUsedStudentIndex = -1;
    private final List<Course> courses = new ArrayList<>();
    private final List<Lector> assistants = new ArrayList<>();
    private final List<Lector> professors = new ArrayList<>();


    Predicate<String> courseExists = (name) -> {
        for (Course c: courses) {
            if (Objects.equals(c.getName(), name)) return true;
        }
        return false;
    };

    @Override
    public Course createCourse(String courseName) {
        if (courseExists.test(courseName)) {
            throw new RuntimeException("The course already exists in db");
        }
        Course course = new Course(courseName);
        courses.add(course);
        return course;
    }

    @Override
    public boolean deleteCourse(String courseName) throws CourseNotFoundException {
        if (!courseExists.test(courseName)) {
            throw new CourseNotFoundException("Course not found");
        }
        courses.removeIf(c -> c.getName().equals(courseName));
        return true;
    }

    @Override
    public Student createStudent(int id, String firstName, String lastName, String facNumber) {
        Student student = new Student(id, firstName, lastName, facNumber);
        students[++lastUsedStudentIndex] = student;
        return student;
    }

    @Override
    public boolean deleteStudent(int id) throws StudentNotFoundException {
        int studentIndex = -1;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId() == id) {
                studentIndex = i;
                break;
            }
        }
        if (studentIndex < 0) throw new StudentNotFoundException("Student with this ID was not found");

        Student[] temp = new Student[NUM_OF_STUDENTS];

        int j = 0;
        for (Student student : students) {
            if (student != null && student.getId() != id) {
                temp[j] = student;
                j++;
            }
        }
        students = temp;
        lastUsedStudentIndex--;
        return true;
    }

    @Override
    public Lector createAssistance(int id, String firstName, String lastName) {
        Lector assistant = new Lector(id, firstName, lastName, LectorType.ASSISTANT);
        this.assistants.add(assistant);
        return assistant;
    }

    @Override
    public boolean deleteAssistance(int id) {
        return assistants.removeIf(a -> a.getId() == id);
    }

    @Override
    public boolean assignAssistanceToCourse(Lector assistance, Course course) {
        if (!assistance.addCourse(course)) {
            return false;
        }
        course.setAssistant(assistance);
        return true;
    }

    @Override
    public Lector createProfessor(int id, String firstName, String lastName, LectorType lectorType) {
        Lector lector = new Lector(id, firstName, lastName, lectorType);
        this.professors.add(lector);
        return lector;
    }

    @Override
    public boolean assignProfessorToCourse(Lector professor, Course course) {
        if (!professor.addCourse(course)) {
            return false;
        }
        course.setLector(professor);
        return true;
    }

    @Override
    public boolean addStudentToCourse(Student student, Course course) {
        if (course.getStudents().size() >= Course.MAX_STUDENTS) {
            throw new RuntimeException("Course already has max number of students");
        }
        if (student.getCourses().size() >= Student.MAX_COURSES) {
            throw new RuntimeException("Student already has max number of courses");
        }
        student.addCourse(course);
        course.addStudent(student);
        return true;
    }

    @Override
    public boolean addStudentsToCourse(Student[] students, Course course) {
        if (course.getStudents().size() + students.length > Course.MAX_STUDENTS) {
            throw new RuntimeException("You can't add this amount of students to this course");
        }
        for (Student s: students) {
            if (s.getCourses().size() >= Student.MAX_COURSES) {
                throw new RuntimeException("At least one of the students can't take more courses");
            }
        }
        for (Student s: students) {
            s.addCourse(course);
            course.addStudent(s);
        }
        return true;
    }

    @Override
    public boolean removeStudentFromCourse(Student student, Course course) throws StudentNotFoundException {
        if (!course.getStudents().contains(student)) {
            throw new StudentNotFoundException("This student is not enrolled to this course");
        }
        course.deleteStudent(student);
        return true;
    }

    public Optional<Student> findStudentById(int id) {
        Student student = null;
        for (Student value : students) {
            if (value != null && value.getId() == id) {
                student = value;
            }
        }
        return Optional.ofNullable(student);
    }

    public Optional<Course> getCourseByName(String name) {
        return courses.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    public Optional<Lector> findAssistantById(int id) {
        return assistants.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
    }

    public Optional<Lector> findProfessorById(int id) {
        return professors.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

}
