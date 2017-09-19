package com.example.priya.contactdemoproject.pojo;

/**
 * Created by priya on 19/9/17.
 */

public class ContactList {
    String name;
    String Number;

    public String getName() {
        return name;
    }

    public ContactList() {
    }

    public ContactList(String name, String number) {
        this.name = name;
        Number = number;

    }

    public void setName(String name) {

        this.name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
