package com.studrecord.StudRecord.models;

public class EnrolledDisciplines {
    private int id;
    private int student_id;
    private int discipline_id;
    private String date;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getDiscipline_id() {
        return discipline_id;
    }

    public void setDiscipline_id(int discipline_id) {
        this.discipline_id = discipline_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
