package com.eduproject.studentservices.service;

import com.eduproject.studentservices.model.Course;
import com.eduproject.studentservices.model.Student;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private static final List<Student> students = new ArrayList<>();
    private static final List<Course> courses = new ArrayList<>();
    private final SecureRandom random = new SecureRandom();

    static {
        Course courseOne = new Course("1", "Spring", "10 Steps",
                List.of("Learn Maven", "Import Project", "First Example"));

        Course courseTwo = new Course("2", "Spring Fundamentals", "10 Steps on starting",
                List.of("Learn Maven", "Import Project", "First Example"));

        Course courseThree = new Course("3", "Job Starting", "1 Steps on starting job",
                List.of("Learn dyn", "Starting Kinh", "Move Example"));

        Student ranga = new Student("Student1", "Ranga Karanam", "Hiker, Programmer and Architect",
                new ArrayList<>(List.of(courseTwo, courseThree)));

        Student satish = new Student("Student2", "Satish T", "Hiker, Programmer and Architect",
                new ArrayList<>(List.of(courseOne, courseTwo, courseThree)));

        students.add(ranga);
        students.add(satish);
        courses.add(courseOne);
        courses.add(courseTwo);
        courses.add(courseThree);

    }

    public List<Student> retrieveAllStudents() {
        return students;
    }

    public Student retrieveStudent(String studentId) {
        return students.stream().filter(student -> student.ID().equals(studentId)).findAny().orElse(null);
    }

    public List<Course> retrieveCourses(String studentId) {
        Student student = retrieveStudent(studentId);
        return student == null ? null : student.courses();

    }

    public Course retrieveCourse(String studentId, String courseId) {
        Student student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }
        return student.courses().stream()
                .filter(course -> course.ID().equals(courseId))
                .findAny()
                .orElse(null);

    }

    public Course addCourse(String studentId, Course course) {
        Student student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        String randomId = new BigInteger(130, random).toString(32);
        student.courses().add(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course getCourse(String courseId) {
        return courses.stream().filter(course -> course.ID().equals(courseId)).findAny().orElse(null);
    }
}
