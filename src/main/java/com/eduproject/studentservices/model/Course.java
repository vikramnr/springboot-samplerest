package com.eduproject.studentservices.model;
import  java.util.List;

public record Course(String ID, String name, String description, List<String> list ){
}
