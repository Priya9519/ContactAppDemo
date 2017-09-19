package com.example.priya.contactdemoproject.pojo;

/**
 * Created by priya on 15/9/17.
 */

public class DataModel {
    String number;
    String dateTime;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public DataModel() {
    }

    public DataModel(String number, String dateTime) {
        this.number = number;
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
