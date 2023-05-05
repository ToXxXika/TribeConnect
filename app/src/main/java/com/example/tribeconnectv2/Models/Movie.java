package com.example.tribeconnectv2.Models;

public class Movie {
    private String title;
    private String description;
    private String image;
    private String video;

    public Movie() {
    }

    public Movie(String title, String description, String image, String video) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.video = video;
    }
    public Movie(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() { return image; }

    public String getVideo() { return video; }
}
