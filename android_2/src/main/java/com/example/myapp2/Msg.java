package com.example.myapp2;

public class Msg {
    private String name;
    private String time;
    private int imageId;

    public Msg(String name, String time, int imageId) {
        this.name = name;
        this.time = time;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getImageId() {
        return imageId;
    }
}
