package org.example.services;


import org.example.beans.Course;
import org.example.beans.Lector;
import org.example.beans.LectorType;
import org.example.beans.Student;

public class CommandParser {

    public static final String CREATE_STUDENT = "createStudent";
    public static final String CREATE_COURSE = "createCourse";
    public static final String CREATE_ASSISTANCE = "createAssistance";
    public static final String CREATE_PROFESSOR = "createProfessor";
    public static final String ASSIGN_STUDENT_TO_COURSE = "assignStudentToCourse";
    public static final String ASSIGN_ASSISTANCE_TO_COURSE = "assignAssistanceToCourse";
    public static final String ASSIGN_PROFESSOR_TO_COURSE = "assignProfessorToCourse";

    private final UniManagementImpl uniManagement = new UniManagementImpl();

    public void parseCommand(String[] input) {

        switch (input[0]) {
            case CREATE_STUDENT -> {
                createStudent(input);
            }
            case CREATE_COURSE -> {
                createCourse(input);
            }
            case CREATE_ASSISTANCE -> {
                createAssistance(input);
            }
            case CREATE_PROFESSOR -> {
                createProfessor(input);
            }
            case ASSIGN_STUDENT_TO_COURSE -> {
                assignStudentToCourse(input);
            }
            case ASSIGN_ASSISTANCE_TO_COURSE -> {
                assignAssistanceToCourse(input);
            }
            case ASSIGN_PROFESSOR_TO_COURSE -> {
                assignProfessorToCourse(input);
            }
            default -> {
                System.out.println("Wrong command entered...\n");
            }
        }

    }

    public void createStudent(String[] input) {
        if (input.length < 5) {
            System.out.println("Incorrect command. Example: createStudent <user_id> <userFirstName> <userLastName> <facNumber>\n");
            return;
        }
        uniManagement.createStudent(Integer.parseInt(input[1]), input[2], input[3], input[4]);
        System.out.println("Student has been created\n");
    }

    public void createCourse(String[] input) {
        if (input.length < 2) {
            System.out.println("Incorrect command. Example: createCourse <courseName>\n");
            return;
        }
        uniManagement.createCourse(input[1]);
        System.out.println("Course has been created\n");
    }

    public void createAssistance(String[] input) {
        if (input.length < 4) {
            System.out.println("Incorrect command. Example: createAssistance <user_id> <userFirstName> <userLastName>\n");
            return;
        }
        uniManagement.createAssistance(Integer.parseInt(input[1]), input[2], input[3]);
        System.out.println("Assistance has been created\n");
    }

    public void createProfessor(String[] input) {
        if (input.length < 5) {
            System.out.println("Incorrect command. Example: createProfessor <user_id> <userFirstName> <userLastname> <lectorType>\n");
            return;
        }
        uniManagement.createProfessor(Integer.parseInt(input[1]), input[2], input[3], LectorType.valueOf(input[4]));
        System.out.printf("Lector (%s) has been created\n\n", input[4]);
    }

    public void assignStudentToCourse(String[] input) {
        if (input.length < 3) {
            System.out.println("Incorrect command. Example: assignStudentToCourse <student_id> <courseName>\n");
            return;
        }
        Student student = uniManagement.findStudentById(Integer.parseInt(input[1]))
                .orElseThrow(() -> new RuntimeException("Student with this id was not found"));
        Course course = uniManagement.getCourseByName(input[2])
                .orElseThrow(() -> new RuntimeException("Course with this name was not found"));
        uniManagement.addStudentToCourse(student, course);
        System.out.println("Student was assigned to course");
    }

    public void assignAssistanceToCourse(String[] input) {
        if (input.length < 3) {
            System.out.println("Incorrect command. Example: assignAssistanceToCourse <assistant_id> <courseName>\n");
            return;
        }
        Lector assistant = uniManagement.findAssistantById(Integer.parseInt(input[1]))
                .orElseThrow(() -> new RuntimeException("Assistant with this id was not found"));
        Course course = uniManagement.getCourseByName(input[2])
                .orElseThrow(() -> new RuntimeException("Course with this name was not found"));
        uniManagement.assignAssistanceToCourse(assistant, course);
        System.out.println("Assistant was assigned to course");
    }

    public void assignProfessorToCourse(String[] input) {
        if (input.length < 3) {
            System.out.println("Incorrect command. Example: assignProfessorToCourse <assistant_id> <courseName>\n");
            return;
        }
        Lector professor = uniManagement.findProfessorById(Integer.parseInt(input[1]))
                .orElseThrow(() -> new RuntimeException("Professor with this id was not found"));
        Course course = uniManagement.getCourseByName(input[2])
                .orElseThrow(() -> new RuntimeException("Course with this name was not found"));
        uniManagement.assignProfessorToCourse(professor, course);
        System.out.println("Professor was assigned to course");
    }


}
