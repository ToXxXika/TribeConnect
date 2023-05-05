package com.example.tribeconnectv2.Models;

public class Reservation {
    String userId;
    String movieId;
    int place;
    String reservationId;

    public Reservation() {
    }
    public String generateRandomId(){
        return String.valueOf((int) (Math.random() * 1000000));
    }
    public Reservation(String userId, String movieId, int place) {
        this.userId = userId;
        this.movieId = movieId;
        this.place = place;
        this.reservationId = generateRandomId();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
