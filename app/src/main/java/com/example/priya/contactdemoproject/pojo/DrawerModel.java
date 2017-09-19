package com.example.priya.contactdemoproject.pojo;

/**
 * Created by priya on 19/9/17.
 */

public class DrawerModel {
    String name;
    int image;

    public DrawerModel(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public DrawerModel() {
    }

    public String getName() {
        return name;


    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
