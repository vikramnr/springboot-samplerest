package com.eduproject.studentservices.controller;

import com.eduproject.studentservices.model.Course;
import com.eduproject.studentservices.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students/{studentId}/courses")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public List<Course> retrieveCoursesForStudent(@PathVariable String studentId) {
        return studentService.retrieveCourses(studentId);
    }

    @GetMapping("/{courseId}")
    public Course retrieveDetailsForCourse(
            @PathVariable String studentId,
            @PathVariable String courseId) {
        return studentService.retrieveCourse(studentId, courseId);
    }

    @PostMapping("/addcourse")
    public ResponseEntity<Void> registerCourseForStudent
            (@PathVariable String studentId,
             @RequestBody Course newCourse) {
        Course course = studentService.addCourse(studentId,newCourse);

        if(course == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(course.ID()).toUri();

        return  ResponseEntity.created(location).build();
    }
}
