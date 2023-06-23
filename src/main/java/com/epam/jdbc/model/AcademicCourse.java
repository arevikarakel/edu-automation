package com.epam.jdbc.model;

public class AcademicCourse {

    private int id;
    private String name;
    private int subjectId;

    public AcademicCourse() {
    }

    public AcademicCourse(int id, String name, int subjectId) {
        this.id = id;
        this.name = name;
        this.subjectId = subjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
