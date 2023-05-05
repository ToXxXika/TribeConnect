package com.example.tribeconnectv2.DatabaseHandler;

import android.util.Log;
import com.example.tribeconnectv2.Models.Utilisateur;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseHandler {


    public FirebaseHandler() {
    }

    public void register(Utilisateur user, FirebaseFirestore db) {
        db.collection("user").add(user).addOnSuccessListener(documentReference -> {
            Log.d("Login", "User added with id : " + documentReference.getId());
        }).addOnFailureListener(e -> {
            Log.e("Login", "User not added : " + e.getMessage());
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

    public interface LoginCallBack {
        void onLogin(boolean res);
    }

    public interface SignupCallBack {
        void onSignup(boolean res);
    }


}
