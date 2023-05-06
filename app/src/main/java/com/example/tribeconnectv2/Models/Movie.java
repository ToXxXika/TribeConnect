package com.example.tribeconnectv2.Models;

public class Movie {
    private String title;
    private String description;
    private int image;
    private String video;

    public Movie() {
    }

    public Movie(String title, String description, int image, String video) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.video = video;
    }
    public Movie(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }
    public Movie (String title, int image ){
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() { return image; }

    public String getVideo() { return video; }
}
