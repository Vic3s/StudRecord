package com.studrecord.StudRecord.models;

import java.util.ArrayList;
import java.util.List;

public class SingleItem {
    private int id;
    private String firstName;
    private String lastName;
    private int ucn;
    private ArrayList<EnrolledDisciplines> enrolledDisciplines;
    private ArrayList<Grade> grades;
    private ArrayList<SemesterGrade> semesterGrades;
    private ArrayList<Complaint> complaints;
    private ArrayList<Commendation> commendations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUcn() {
        return ucn;
    }

    public void setUcn(int ucn) {
        this.ucn = ucn;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = new ArrayList<>(grades);
    }

    public ArrayList<SemesterGrade> getSemesterGrades() {
        return semesterGrades;
    }

    public void setSemesterGrades(List<SemesterGrade> semesterGrades) {
        this.semesterGrades = new ArrayList<>(semesterGrades);
    }

    public ArrayList<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = new ArrayList<>(complaints);
    }

    public ArrayList<Commendation> getCommendations() {
        return commendations;
    }

    public void setCommendations(List<Commendation> commendations) {
        this.commendations = new ArrayList<>(commendations);
    }

    public ArrayList<EnrolledDisciplines> getEnrolledDisciplines() {
        return enrolledDisciplines;
    }

    public void setEnrolledDisciplines(List<EnrolledDisciplines> enrolledDisciplines) {
        this.enrolledDisciplines = new ArrayList<>(enrolledDisciplines);
    }


}
