package com.studrecord.StudRecord;
import com.studrecord.StudRecord.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class DbHelper {

    @Autowired
    private static DbConfig dbConfig;

    public static Connection CreateConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(
                    dbConfig.getDbUrl(),
                    dbConfig.getDbUser(),
                    dbConfig.getDbPassword());
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
                    singleItem.setClass_(rs.getString("class"));
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

            // Get all the commendations for a student by id
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

    public static ArrayList<SingleItem> GetAllItems(Connection conn){
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

    public static ArrayList<SingleItem> GetMultipleItems(String condition, Connection conn){
        ArrayList<SingleItem> multipleItems = new ArrayList<>();

        try{
            String query_w_condition =  String.format("SELECT DISTINCT * FROM stud_full_view WHERE %s;", condition);

            try(PreparedStatement stm = conn.prepareStatement(query_w_condition)){
                ResultSet rs = stm.executeQuery();

                while(rs.next()){
                    SingleItem student = GetSingleItem(rs.getInt("student_id"), conn);
                    multipleItems.add(student);
                }
            }catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }catch(Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }

        return multipleItems;
    }

//    Create Section

//    Insert Single Item
    public static void InsertSingleItem(Connection conn, SingleItem itemData){

        String query = "INSERT INTO students (id, first_name, last_name, ucn, class) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement stm = conn.prepareStatement(query)){
            stm.setInt(1, itemData.getId());
            stm.setString(2, itemData.getFirstName());
            stm.setString(3, itemData.getLastName());
            stm.setInt(4, itemData.getUcn());
            stm.setString(5, itemData.getClass_());

            stm.executeUpdate();
            System.out.println("Student Added...");

        }catch (Exception ex){
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }

//    Insert Multiple Items
    public static void InsertMultipleItems(Connection conn, ArrayList<SingleItem> itemDataList){
        String query = "INSERT INTO students (id, first_name, last_name, ucn, class) VALUES (?, ?, ?, ?, ?)";

        for(SingleItem itemData : itemDataList){
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, itemData.getId());
                stm.setString(2, itemData.getFirstName());
                stm.setString(3, itemData.getLastName());
                stm.setInt(4, itemData.getUcn());
                stm.setString(5, itemData.getClass_());

                stm.executeUpdate();
            }catch(Exception ex){
                System.out.println("Something went wrong: " + ex.getMessage());
            }
        }
        System.out.println("Multiple Students Added...");
    }

//     Deletion Section

    public static void DeleteSingleItem(Connection conn, int id){
        String query_students = "DELETE FROM students WHERE id=?";
        String query_enrolled_disciplines = "DELETE FROM enrolled_disciplines WHERE student_id=?";
        String query_commendations = "DELETE FROM commendations WHERE student_id=?";
        String query_complaints = "DELETE FROM complaints WHERE student_id=?";
        String query_grades = "DELETE FROM grades WHERE student_id=?";
        String query_semester_grades = "DELETE FROM semester_grades WHERE student_id=?";

        try{
            conn.setAutoCommit(false);
            try(PreparedStatement stm_commendations = conn.prepareStatement(query_commendations);
                PreparedStatement stm_complaints  = conn.prepareStatement(query_complaints);
                PreparedStatement stm_grades = conn.prepareStatement(query_grades);
                PreparedStatement stm_semester_grades = conn.prepareStatement(query_semester_grades);
                PreparedStatement stm_enrolled_disciplines = conn.prepareStatement(query_enrolled_disciplines);
                PreparedStatement stm_students = conn.prepareStatement(query_students);
            ){
                stm_commendations.setInt(1, id);
                stm_complaints.setInt(1, id);
                stm_grades.setInt(1, id);
                stm_semester_grades.setInt(1, id);
                stm_enrolled_disciplines.setInt(1, id);
                stm_students.setInt(1, id);

                stm_commendations.executeUpdate();
                stm_complaints.executeUpdate();
                stm_grades.executeUpdate();
                stm_semester_grades.executeUpdate();
                stm_enrolled_disciplines.executeUpdate();
                stm_students.executeUpdate();
            }
            conn.commit();
            System.out.println("Removed All Information Related To Item");

        }catch(Exception ex){
            System.out.println("Something Went Wrong: " + ex.getMessage());
            try{
                conn.rollback();
            } catch (Exception ignored) {}
        }finally {
            try {
                conn.setAutoCommit(true);
            } catch (Exception ignored) {}
        }

    }

    public static void DeleteMultipleItem(Connection conn, ArrayList<Integer> ids){
        String query_students = "DELETE FROM students WHERE id=?";
        String query_enrolled_disciplines = "DELETE FROM enrolled_disciplines WHERE student_id=?";
        String query_commendations = "DELETE FROM commendations WHERE student_id=?";
        String query_complaints = "DELETE FROM complaints WHERE student_id=?";
        String query_grades = "DELETE FROM grades WHERE student_id=?";
        String query_semester_grades = "DELETE FROM semester_grades WHERE student_id=?";

        try{
            conn.setAutoCommit(false);

            try(PreparedStatement stm_commendations = conn.prepareStatement(query_commendations);
                PreparedStatement stm_complaints  = conn.prepareStatement(query_complaints);
                PreparedStatement stm_grades = conn.prepareStatement(query_grades);
                PreparedStatement stm_semester_grades = conn.prepareStatement(query_semester_grades);
                PreparedStatement stm_enrolled_disciplines = conn.prepareStatement(query_enrolled_disciplines);
                PreparedStatement stm_students = conn.prepareStatement(query_students);
            ){
                for (int id : ids) {
                    stm_commendations.setInt(1, id);
                    stm_complaints.setInt(1, id);
                    stm_grades.setInt(1, id);
                    stm_semester_grades.setInt(1, id);
                    stm_enrolled_disciplines.setInt(1, id);
                    stm_students.setInt(1, id);

                    stm_commendations.executeUpdate();
                    stm_complaints.executeUpdate();
                    stm_grades.executeUpdate();
                    stm_semester_grades.executeUpdate();
                    stm_enrolled_disciplines.executeUpdate();
                    stm_students.executeUpdate();
                }
            }
            conn.commit();
            System.out.println("Removed All Information Related To Items");
        }catch(Exception ex){
            try{
                conn.rollback();
            } catch (Exception ignored) {}
            System.out.println("Something Went Wrong: " + ex.getMessage());
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (Exception ignored) {}
        }
    }


//    Update Section

    public static void UpdateSingleItem(Connection conn, HashMap<String, String> updateData, int id){
        if (updateData.isEmpty()) {
            throw new IllegalArgumentException("No fields to update");
        }

        StringBuilder query = new StringBuilder("UPDATE students SET ");
        List<Object> values = new ArrayList<>();

        for (String column : updateData.keySet()) {
            query.append(column).append(" = ?, ");
            values.add(updateData.get(column));
        }

        query.setLength(query.length() - 2);
        query.append(" WHERE id = ?");

        try(PreparedStatement stm = conn.prepareStatement(query.toString())){
            int index = 1;

            for(Object value: values){
                stm.setObject(index++, value);
            }

            stm.setInt(index, id);
            stm.executeUpdate();

        }catch (Exception ex){
            throw new RuntimeException("Update failed", ex);
        }

        System.out.println("Updated User Data Successfully");
    }
}
