package com.studrecord.StudRecord;
import com.studrecord.StudRecord.models.SingleItem;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.ArrayList;

@SpringBootApplication
public class StudRecordApplication {

	public static void main(String[] args) {

		try(Connection conn = DbHelper.CreateConnection();){
			ArrayList<SingleItem> multipleStudents = DbHelper.GetMultipleItems("student_id=1083 AND discipline_id=100", conn);

			for(int i=0; i<multipleStudents.size(); i++){
				System.out.println(multipleStudents.get(i).getFirstName() + " " + multipleStudents.get(i).getLastName());
			}
		}catch (Exception ex){
			System.out.println(ex.getMessage());
		}

	}

}
