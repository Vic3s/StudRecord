package com.studrecord.StudRecord.models;

public class Commendation {
    private int id;
    private int student_id;
    private String commendation_information;
    private String Date;


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

    public String getCommendation_information() {
        return commendation_information;
    }

    public void setCommendation_information(String commendation_information) {
        this.commendation_information = commendation_information;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
