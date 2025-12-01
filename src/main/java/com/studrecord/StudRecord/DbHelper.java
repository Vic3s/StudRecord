package com.studrecord.StudRecord;
import com.studrecord.StudRecord.models.*;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DbHelper {

    @Value("${database.connection.string}")
    private String dbConnectionString;

    @Value("${database.owner}")
    private String dbOwner;

    @Value("${database.password}")
    private String dbPassword;

    private Connection CreateConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(dbConnectionString, dbOwner, dbPassword);
            System.out.println("Connection established successfully");

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return conn;
    }

    public static SingleItem GetSingleItem(int id){
        SingleItem singleItem = new SingleItem();

        // functionality to map returned database item to
        // SingleItem object and return afterward

        return singleItem;
    }

    public static ArrayList<SingleItem> getAllItems(){
        ArrayList<SingleItem> allItems = new ArrayList<>();

        // functionality to get all items one by one with a loop
        // instantiate them as a SingleItem objects and map them
        // and then put in the arraylist

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
