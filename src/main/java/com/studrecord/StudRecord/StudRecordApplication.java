package com.studrecord.StudRecord;
import com.studrecord.StudRecord.models.SingleItem;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

@SpringBootApplication
public class StudRecordApplication {

	public static void main(String[] args) {
		HashMap<String, String> UpdateKeyValues = new HashMap<>();

		UpdateKeyValues.put("first_name", "Nathaniel");
		UpdateKeyValues.put("last_name", "Pegasus");

		try(Connection conn = DbHelper.CreateConnection();){
			DbHelper.UpdateSingleItem(conn, UpdateKeyValues, 1083);

		}catch (Exception ex){
			System.out.println(ex.getMessage());
		}

	}

}
