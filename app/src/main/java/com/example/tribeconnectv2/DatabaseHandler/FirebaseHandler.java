package com.example.tribeconnectv2.DatabaseHandler;

import android.util.Log;
import com.example.tribeconnectv2.Models.Movie;
import com.example.tribeconnectv2.Models.Utilisateur;
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
    public void bookMovie(int MovieId, String userId, FirebaseFirestore db, bookMovieCallBack callBack) {
        db.collection("movies").document(String.valueOf(MovieId)).update("booked", true, "userId", userId).addOnSuccessListener(aVoid -> {
            Log.d("bookMovie", "Movie booked");
            callBack.onBookMovie(true);
        }).addOnFailureListener(e -> {
            Log.e("bookMovie", "Movie not booked : " + e.getMessage());
            callBack.onBookMovie(false);
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

    public interface LoginCallBack {
        void onLogin(boolean res);
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


}
