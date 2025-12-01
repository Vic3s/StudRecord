package com.studrecord.StudRecord;
import com.studrecord.StudRecord.models.SingleItem;

public class StudRecordApplication {

	public static void main(String[] args) {
		SingleItem student = DbHelper.GetSingleItem(1083);
	}

}
