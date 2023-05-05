package com.example.tribeconnectv2.Models;

public class Salle {
    int places ;
    String salleId;
    String movieId;

    public Salle() {
    }

    public Salle(int places, String salleId, String movieId) {
        this.places = places;
        this.salleId = salleId;
        this.movieId = movieId;
    }

    public String getSalleId() {
        return salleId;
    }

    public void setSalleId(String salleId) {
        this.salleId = salleId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }
}
