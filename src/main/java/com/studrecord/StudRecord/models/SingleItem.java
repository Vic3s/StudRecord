package com.studrecord.StudRecord.models;

import java.util.ArrayList;
import java.util.List;

public class SingleItem {
    public int id;
    public String firstName;
    public String lastName;
    public int ucn;
    public ArrayList<Grade> grades;
    public ArrayList<SemesterGrade> semesterGrades;
    public ArrayList<Complaint> complaints;
    public ArrayList<Commendation> commendations;
    public ArrayList<String> enrolledDisciplines;

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

    public ArrayList<String> getEnrolledDisciplines() {
        return enrolledDisciplines;
    }

    public void setEnrolledDisciplines(List<String> enrolledDisciplines) {
        this.enrolledDisciplines = new ArrayList<>(enrolledDisciplines);
    }


}
