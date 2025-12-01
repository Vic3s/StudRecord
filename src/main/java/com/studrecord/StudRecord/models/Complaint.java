package com.studrecord.StudRecord.models;

public class Complaint {
    private int id;
    private int student_id;
    private String complaint_information;
    private String date;


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

    public String getComplaint_information() {
        return complaint_information;
    }

    public void setComplaint_information(String complaint_information) {
        this.complaint_information = complaint_information;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
