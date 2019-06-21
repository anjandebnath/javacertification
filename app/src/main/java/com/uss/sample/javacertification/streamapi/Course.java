package com.uss.sample.javacertification.streamapi;

import java.util.List;

public class Course {

    private List<Student> students;
    private String name;

    public Course(String name, List<Student> students){
        this.name = name;
        this.students = students;
    }
    public List<Student> getStudents() {
        return students;
    }
    public String getName() {
        return name;
    }

}
