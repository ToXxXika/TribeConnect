package com.example.tribeconnectv2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tribeconnectv2.DatabaseHandler.FirebaseHandler;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextInputEditText email, password;
    MaterialButton login;
    TextView register;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_main);
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
            if (passwordText.length() < 6) {
                password.setError("Password should be atleast 6 characters long");
                password.requestFocus();
                return;
            }
            FirebaseHandler FH = new FirebaseHandler();
            FH.login(emailText, passwordText, db, new FirebaseHandler.LoginCallBack() {
                        @Override
                        public void onLogin(boolean res) {
                            Toast toast;
                            if (res) {
                                toast = Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT);
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                toast = Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT);
                            }
                            toast.show();
                        }
                    }
            );
        });


    }
}
