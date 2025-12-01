package com.studrecord.StudRecord.models;

public class SemesterGrade {
    private int id;
    private int discipline_id;
    private int student_id;
    private int grade;
    private String semester;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscipline_id() {
        return discipline_id;
    }

    public void setDiscipline_id(int discipline_id) {
        this.discipline_id = discipline_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
