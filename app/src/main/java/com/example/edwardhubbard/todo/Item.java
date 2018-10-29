package com.example.edwardhubbard.todo;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;


/*
Prepare: has 5 string properties that define a todo list item

Input: the input is the constructors that takes the properties(times optional) and initializes them

Process: Properties are initialized and stored

Output: Getters. can retrieve any property
 */
public class Item implements Serializable{
    //properties
    private final String itemName;
    private final String itemDate;
    private final String itemDetails;
    private final String itemStartTime;
    private final String itemEndTime;


    //Constructor for item with no times. never used because strings can be empty
    public Item(String name, String date, String details){
        this.itemName = name;
        this.itemDate = date;
        this.itemDetails = details;
        this.itemStartTime = null;
        this.itemEndTime = null;



    }
    // Constructor that takses all properties
    public Item(String name, String date, String startTime, String endTime, String details){
        this.itemName = name;
        this.itemDate = date;
        this.itemDetails = details;
        this.itemStartTime = startTime;
        this.itemEndTime = endTime;


    }
    // gets Item name
    public String getItemName() {
        return itemName;
    }
    //gets Item date
    public String getItemDate() {
        return itemDate;
    }
    // gets start time
    public String getItemStartTime() {
        return itemStartTime;
    }
    //gets end time
    public String getItemEndTime() {
        return itemEndTime;
    }
    // gets user set Details
    public String getItemDetails() {
        return itemDetails;
    }
}
