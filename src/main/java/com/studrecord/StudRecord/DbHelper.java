package com.studrecord.StudRecord;
import com.studrecord.StudRecord.models.*;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {

//    @Value("${database.connection.string}")
    private static String dbConnectionString = "jdbc:postgresql://ep-silent-wildflower-a41p6ovg-pooler.us-east-1.aws.neon.tech/StudRecord?sslmode=require&channel_binding=require";

//    @Value("${database.owner}")
    private static String dbOwner = "neondb_owner";

//    @Value("${database.password}")
    private static String dbPassword = "npg_s0QBEy9oWLKT";

    public static Connection CreateConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(dbConnectionString, dbOwner, dbPassword);
            System.out.println("Connection established successfully");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return conn;
    }

    public static SingleItem GetSingleItem(int id, Connection conn){
        SingleItem singleItem = new SingleItem();

        try{
            // Get basic student information by id
            String query_stud_info = "SELECT * FROM students WHERE id=?;";

            try(PreparedStatement stm = conn.prepareStatement(query_stud_info) ){
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                while(rs.next()){
                    singleItem.setId(id);
                    singleItem.setFirstName(rs.getString("first_name"));
                    singleItem.setLastName(rs.getString("last_name"));
                    singleItem.setUcn(rs.getInt("ucn"));
                }
            }catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }

            // Get the enrolled disciplines for the semester for a student by id
            String query_enrolled_disciplines = "SELECT * FROM Enrolled_Disciplines WHERE student_id=?";

            try(PreparedStatement stm = conn.prepareStatement(query_enrolled_disciplines)){
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                List<EnrolledDisciplines> enrolledDisciplinesList = new ArrayList<>();

                while(rs.next()){
                    EnrolledDisciplines enrolledDisciplinesObj = new EnrolledDisciplines();
                    enrolledDisciplinesObj.setId(rs.getInt("id"));
                    enrolledDisciplinesObj.setStudent_id(rs.getInt("student_id"));
                    enrolledDisciplinesObj.setDiscipline_id(rs.getInt("discipline_id"));
                    enrolledDisciplinesObj.setDate(rs.getString("date"));
                    enrolledDisciplinesObj.setStatus(rs.getString("status"));

                    enrolledDisciplinesList.add(enrolledDisciplinesObj);
                }

                singleItem.setEnrolledDisciplines(enrolledDisciplinesList);
            }catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }

            // Get the grades of a student by id
            String query_stud_grades = "SELECT * FROM Grades WHERE student_id=?;";

            try(PreparedStatement stm = conn.prepareStatement(query_stud_grades)){
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                List<Grade> gradesList = new ArrayList<>();

                while(rs.next()){
                    Grade gradeObj = new Grade();
                    gradeObj.setId(rs.getInt("id"));
                    gradeObj.setDiscipline_id(rs.getInt("discipline_id"));
                    gradeObj.setStudent_id(rs.getInt("student_id"));
                    gradeObj.setGrade(rs.getInt("grade"));
                    gradeObj.setDate(rs.getString("date"));

                    gradesList.add(gradeObj);
                }
                singleItem.setGrades(gradesList);

            }catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }

            // Get all semester grades for a student by id
            String query_stud_grades_semester = "SELECT * FROM Semester_Grades WHERE student_id=?;";

            try(PreparedStatement stm = conn.prepareStatement(query_stud_grades_semester)){
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                List<SemesterGrade> semesterGradesList = new ArrayList<>();

                while(rs.next()){
                    SemesterGrade semesterGradeObj = new SemesterGrade();
                    semesterGradeObj.setId(rs.getInt("id"));
                    semesterGradeObj.setDiscipline_id(rs.getInt("discipline_id"));
                    semesterGradeObj.setStudent_id(rs.getInt("student_id"));
                    semesterGradeObj.setGrade(rs.getInt("grade"));
                    semesterGradeObj.setSemester(rs.getString("semester"));

                    semesterGradesList.add(semesterGradeObj);
                }
                singleItem.setSemesterGrades(semesterGradesList);

            }catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }

            // Get the all commendations for a student by id
            String query_stud_commendations = "SELECT * FROM Commendations WHERE student_id=?;";

            try(PreparedStatement stm = conn.prepareStatement(query_stud_commendations)){
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                List<Commendation> CommendationsList = new ArrayList<>();

                while(rs.next()){
                    Commendation commendationObj = new Commendation();
                    commendationObj.setId(rs.getInt("id"));
                    commendationObj.setStudent_id(rs.getInt("student_id"));
                    commendationObj.setCommendation_information(rs.getString("commendation_information"));
                    commendationObj.setDate(rs.getString("date"));

                    CommendationsList.add(commendationObj);
                }
                singleItem.setCommendations(CommendationsList);

            }catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }

            // Get the all complaints for a student by id
            String query_stud_complaints = "SELECT * FROM Complaints WHERE student_id=?;";

            try(PreparedStatement stm = conn.prepareStatement(query_stud_complaints)){
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                List<Complaint> CommendationsList = new ArrayList<>();

                while(rs.next()){
                    Complaint complaintObj = new Complaint();
                    complaintObj.setId(rs.getInt("id"));
                    complaintObj.setStudent_id(rs.getInt("student_id"));
                    complaintObj.setComplaint_information(rs.getString("complaint_information"));
                    complaintObj.setDate(rs.getString("date"));

                    CommendationsList.add(complaintObj);
                }
                singleItem.setComplaints(CommendationsList);

            }catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }

        }catch(Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }

        return singleItem;
    }

    public static ArrayList<SingleItem> getAllItems(Connection conn){
        ArrayList<SingleItem> allItems = new ArrayList<>();

        try{
            String query_all_studs = "SELECT * FROM students";

            try(PreparedStatement stm = conn.prepareStatement(query_all_studs)){
                ResultSet rs = stm.executeQuery();

                while(rs.next()){
                    SingleItem student = GetSingleItem(rs.getInt("id"), conn);
                    allItems.add(student);
                }
            }catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }

        }catch(Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }

        return allItems;
    }

    public static ArrayList<SingleItem> getMultipleItems(String condition){
        ArrayList<SingleItem> multipleItems = new ArrayList<>();

        // using the condition
        // functionality to get multiple items one by one with a loop
        // instantiate them as a SingleItem objects and map them
        // and then put in the arraylist

        return multipleItems;

    }

}
