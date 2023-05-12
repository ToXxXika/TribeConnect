package com.example.tribeconnectv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tribeconnectv2.DatabaseHandler.FirebaseHandler;
import com.example.tribeconnectv2.Models.Utilisateur;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextInputEditText email, password;
    MaterialButton login;
    TextView register;
    FirebaseFirestore db;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        getSupportActionBar().hide();
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        register = findViewById(R.id.textViewRegister);
        login = findViewById(R.id.buttonLogin);
        register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        login.setOnClickListener(v -> {


            String emailText = Objects.requireNonNull(email.getText()).toString();
            String passwordText = Objects.requireNonNull(password.getText()).toString();

            if (emailText.isEmpty()) {
                email.setError("Email is required");
                email.requestFocus();
                return;
            }
            if (passwordText.isEmpty()) {
                password.setError("Password is required");
                password.requestFocus();
                return;
            }

            FirebaseHandler FH = new FirebaseHandler();
            FH.login(emailText, passwordText, db, new FirebaseHandler.LoginCallBack() {
                        @Override
                        public void onLogin(boolean res) {
                            Toast toast;
                            if (res) {
                                //TODO: find user by username and store in shared preferences
                                FH.getUsers(db, new FirebaseHandler.getUsersCallBack() {
                                    @Override
                                    public void onGetUsers(List<Utilisateur> lstUsers) {
                                        for (Utilisateur u: lstUsers) {
                                            if(u.getEmail().equals(emailText)){
                                                Toast x;
                                                editor.putString("Nom", u.getNom());
                                                editor.putString("Prenom", u.getPrenom());
                                                editor.putString("email", u.getEmail());
                                                editor.apply();
                                                x = Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT);
                                                x.show();
                                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                                startActivity(intent);

                                            }
                                        }
                                    }
                                });


                            } else {
                                toast = Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                        }
                    }
            );
        });


    }
}
