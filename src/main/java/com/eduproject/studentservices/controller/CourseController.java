package com.eduproject.studentservices.controller;

import com.eduproject.studentservices.model.Course;
import com.eduproject.studentservices.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses/")
public class CourseController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public List<Course> getAllCourses() {
        return studentService.getAllCourses();
    }
    @GetMapping("/{id}")
    public Course getCourse(@PathVariable String id) {
        return studentService.getCourse(id);
    }
}
