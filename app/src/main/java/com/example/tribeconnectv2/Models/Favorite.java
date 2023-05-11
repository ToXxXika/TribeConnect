package com.example.tribeconnectv2.Models;

public class Favorite {
    String idMovie ;
    String idUser ;
    boolean Liked ;

    public Favorite(String idMovie, String idUser, boolean liked) {
        this.idMovie = idMovie;
        this.idUser = idUser;
        Liked = liked;
    }

    public Favorite() {
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public boolean isLiked() {
        return Liked;
    }

    public void setLiked(boolean liked) {
        Liked = liked;
    }
}
