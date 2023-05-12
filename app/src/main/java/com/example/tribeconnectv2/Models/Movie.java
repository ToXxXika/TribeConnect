package com.example.tribeconnectv2.Models;

public class Movie {
    private String title;
    private String description;
    private String image;

    private String idMovie;

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public Movie() {
    }


    public String generateRandomIdMovie(){
        return "M"+(int)(Math.random()*1000000);
    }
    public Movie(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.idMovie = generateRandomIdMovie();
    }
    public Movie (String title, String image ){
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() { return image; }

}
