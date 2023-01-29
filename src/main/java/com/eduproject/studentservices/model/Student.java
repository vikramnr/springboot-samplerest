package com.eduproject.studentservices.model;

import java.util.List;

public record Student(String ID, String name, String description,
                      List<Course> courses) {
}
