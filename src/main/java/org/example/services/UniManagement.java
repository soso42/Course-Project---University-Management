package org.example.services;

import org.example.beans.Course;
import org.example.beans.Lector;
import org.example.beans.Student;
import org.example.exceptions.CourseNotFoundException;
import org.example.exceptions.StudentNotFoundException;

public interface UniManagement {
    /*** Create a new course with name courseName and returnit
     * ** @return new instance of course with the passedname if it's cerated or* null in antoher case.
     * A course will be createdonly if already does not exists* another course with the same courseName*/
    public Course createCourse(String courseName);

    /*** Delete a course with name courseName
     *  @return <code>true</code> only in case the coursewith passed name was removed
     */
    public boolean deleteCourse(String courseName) throws CourseNotFoundException;

    /*** Create and return new instance of Student withthe passed arguments and initial stateof the student
     * ** @return new instance of a student identified withthe passed ID, if already does notexists,
     * * and the other arguments as initial state if it'scerated or* null in another case*/
    public Student createStudent(int id, String firstName, String lastName, String facNumber);

    /*** Delete a student with the passed ID
     * ** @return <code>true</code> only in cae the studentwas removed*/
    public boolean deleteStudent(int id) throws StudentNotFoundException;

    /*** Create a new assistance in the univertity withthepassed arguemtns as initial state
     * ** @return new created professor assistance identifiedwiththe passed ID, if already doesnot exists with that ID*/
    public Lector createAssistance(int id, String firstName, String lastName);

    /*** Delete an professor asisstance with the passedID, if such exists
     * ** @return <code>true</code> ONLY in case the assistancewas removed*/
    public boolean deleteAssistance(int id);

    /**
     * Aighn an assistance to a course** @return <code>true</code> ONLY n case the assistancewas successfully assigned tothe course
     */
    public boolean assignAssistanceToCourse(Lector assistance,Course course);

    /**
     * Assign a professor to a course
     * @return <code>true</code> ONLY n case the professorwas successfully assigned tothe course
     */
    public boolean assignProfessorToCourse(Lector professor,Course course);

    /** Add a studnt to a course
     * ** @return <code.true</code> ONLY inca se the studentis successfully added to thecourse*/
    public boolean addStudentToCourse(Student student,Course course);

    /**
     *  Add all students to a course** @return <code>true</code>
     *  ONLY inc ase all studentsare added to a course*/
    public boolean addStudentsToCourse(Student[] students,Course course);

    /*** Remove a student from a course
     * ** @return <code>true</code> only in case the studentwas successfully removed from acourse
     * */
    public boolean removeStudentFromCourse(Student student,Course course);

}