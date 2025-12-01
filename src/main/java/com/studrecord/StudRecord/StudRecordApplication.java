package com.studrecord.StudRecord;
import com.studrecord.StudRecord.models.SingleItem;

import java.sql.Connection;
import java.util.ArrayList;

public class StudRecordApplication {

	public static void main(String[] args) {


		try(Connection conn = DbHelper.CreateConnection();){
			ArrayList<SingleItem> allStudents = DbHelper.getAllItems(conn);

			for(int i=0; i<allStudents.size(); i++){
				System.out.println(allStudents.get(i).getFirstName() + " " + allStudents.get(i).getLastName());
			}
		}catch (Exception ex){
			System.out.println(ex.getMessage());
		}


	}

}
