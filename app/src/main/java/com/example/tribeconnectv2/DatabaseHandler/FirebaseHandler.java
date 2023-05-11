package com.example.tribeconnectv2.DatabaseHandler;

import android.util.Log;
import com.example.tribeconnectv2.Models.*;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FirebaseHandler {


    public FirebaseHandler() {
    }


    public void addMovie(Movie movie, FirebaseFirestore db, addMovieCallBack callBack) {
        db.collection("movies").add(movie).addOnSuccessListener(documentReference -> {
            Log.d("addMovie", "Movie added with id : " + documentReference.getId());
            callBack.onAddMovie(true);
        }).addOnFailureListener(e -> {
            Log.e("addMovie", "Movie not added : " + e.getMessage());
            callBack.onAddMovie(false);
        });
    }
    public void getUsers(FirebaseFirestore db, getUsersCallBack callBack){
        db.collection("user").get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.isEmpty()) {
                Log.d("user", "No user found");
                callBack.onGetUsers(null);

            } else {
                Log.d("user", queryDocumentSnapshots.toObjects(Utilisateur.class).toString());
                callBack.onGetUsers(queryDocumentSnapshots.toObjects(Utilisateur.class));
            }
        }).addOnFailureListener(e -> {
            Log.e("user", "Users not found : " + e.getMessage());
        });

    }

    public void bookMovie(Reservation reservation,Salle S, FirebaseFirestore db, bookMovieCallBack callBack) {
        db.collection("reservation").add(reservation).addOnSuccessListener(documentReference -> {
            //update salle places
            db.collection("salles").document(String.valueOf(S.getSalleId())).update("places", (S.getPlaces()-reservation.getPlace())).addOnSuccessListener(aVoid -> {
                Log.d("bookMovie", "Salle places updated");
            }).addOnFailureListener(e -> {
                Log.e("bookMovie", "Salle places not updated : " + e.getMessage());
            });
            Log.d("bookMovie", "Movie booked with id : " + documentReference.getId());
            callBack.onBookMovie(true);
        }).addOnFailureListener(e -> {
            Log.e("bookMovie", "Movie not booked : " + e.getMessage());
            callBack.onBookMovie(false);
        });

    }
    public void unbookMovie(int MovieId, FirebaseFirestore db, bookMovieCallBack callBack) {
        db.collection("movies").document(String.valueOf(MovieId)).update("booked", false, "userId", "").addOnSuccessListener(aVoid -> {
            Log.d("bookMovie", "Movie unbooked");
            callBack.onBookMovie(true);
        }).addOnFailureListener(e -> {
            Log.e("bookMovie", "Movie not unbooked : " + e.getMessage());
            callBack.onBookMovie(false);
        });
    }
    public void getsalles(FirebaseFirestore db, getSallesCallBack callBack){
        db.collection("salles").get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.isEmpty()) {
                Log.d("salles", "No salles found");
                callBack.onGetSalles(null);

            } else {
                Log.d("salles", "Salles found");
                callBack.onGetSalles(queryDocumentSnapshots.toObjects(Salle.class));
            }
        }).addOnFailureListener(e -> {
            Log.e("salles", "Salles not found : " + e.getMessage());
        });
    }
    public void getMovies(FirebaseFirestore db, getMoviesCallBack callBack){
        db.collection("movies").get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.isEmpty()) {
                Log.d("movies", "No movies found");
                callBack.onGetMovies(null);

            } else {
                Log.d("movies", "Movies found");
                callBack.onGetMovies(queryDocumentSnapshots.toObjects(Movie.class));
            }
        }).addOnFailureListener(e -> {
            Log.e("movies", "Movies not found : " + e.getMessage());
        });
    }

    public void login(String mail, String password, FirebaseFirestore db, LoginCallBack callBack) {

        db.collection("user").whereEqualTo("email", mail).whereEqualTo("password", password).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.isEmpty()) {
                Log.d("Login", "User not found");
                callBack.onLogin(false);

            } else {
                Log.d("Login", "User found");
                callBack.onLogin(true);
            }
        }).addOnFailureListener(e -> {
            Log.e("Login", "User not found : " + e.getMessage());
        });

    }
    public void register(Utilisateur u , FirebaseFirestore db, SignupCallBack callBack) {
        db.collection("user").add(u).addOnSuccessListener(documentReference -> {
            Log.d("Login", "User added with id : " + documentReference.getId());
            callBack.onSignup(true);
        }).addOnFailureListener(e -> {
            Log.e("Login", "User not added : " + e.getMessage());
            callBack.onSignup(false);
        });
    }
    public void LikeMovie(FirebaseFirestore db , LikeMovieCallBack callBack,String idMovie,String idUser){
        Favorite f = new Favorite(idMovie,idUser,true);
        db.collection("favorite").add(f).addOnSuccessListener(documentReference -> {
            Log.d("LikeMovie", "Movie Liked with id : " + documentReference.getId());
            callBack.onLikeMovie(true);
        }).addOnFailureListener(e -> {
            Log.e("LikeMovie", "Movie not added : " + e.getMessage());
            callBack.onLikeMovie(false);
        });

    }
    public interface LikeMovieCallBack{
        void onLikeMovie(boolean res);
    }

    public interface LoginCallBack {
        void onLogin(boolean res);
    }
    public interface getUsersCallBack{
        void onGetUsers(List<Utilisateur> lstUsers);
    }
    public interface getMoviesCallBack{
        void onGetMovies(List<Movie> lstMovies);
    }
    public interface addMovieCallBack{
        void onAddMovie(boolean res);
    }

    public interface SignupCallBack {
        void onSignup(boolean res);
    }
    public interface bookMovieCallBack {
        void onBookMovie(boolean res);
    }
    public interface getSallesCallBack{
        void onGetSalles(List<Salle> res);
    }


}
