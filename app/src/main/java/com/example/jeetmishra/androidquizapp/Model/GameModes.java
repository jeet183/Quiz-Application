package com.example.jeetmishra.androidquizapp.Model;

public class GameModes {
    private String Name;
    private String Image;

    public GameModes() {
    }

    public GameModes(String name, String image) {
        this.Name = name;
        this.Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }
}
