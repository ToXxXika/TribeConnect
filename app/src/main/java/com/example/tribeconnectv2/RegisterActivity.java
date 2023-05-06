package com.example.tribeconnectv2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tribeconnectv2.DatabaseHandler.FirebaseHandler;
import com.example.tribeconnectv2.Models.Utilisateur;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText fullname, email, password, confirmpassword;
    TextView signin;
    MaterialButton register;

    FirebaseFirestore db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_register);
        fullname = findViewById(R.id.editTextFullName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        confirmpassword = findViewById(R.id.editTextConfirmPassword);
        register = findViewById(R.id.buttonRegister);
        signin = findViewById(R.id.textViewSignIn);
        signin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        });
        register.setOnClickListener(v -> {
            if (fullname.getText().toString().isEmpty()) {
                fullname.setError("Please enter your full name");
                fullname.requestFocus();
            } else if (email.getText().toString().isEmpty()) {
                email.setError("Please enter your email");
                email.requestFocus();
            } else if (password.getText().toString().isEmpty()) {
                password.setError("Please enter your password");
                password.requestFocus();
            } else if (confirmpassword.getText().toString().isEmpty()) {
                confirmpassword.setError("Please confirm your password");
                confirmpassword.requestFocus();
            } else if (!password.getText().toString().equals(confirmpassword.getText().toString())) {
                confirmpassword.setError("Password does not match");
                confirmpassword.requestFocus();
            } else {
                Utilisateur u = new Utilisateur(fullname.getText().toString(),fullname.getText().toString(), email.getText().toString(), password.getText().toString(),"User");

                FirebaseHandler FH = new FirebaseHandler();
                FH.register(u,db,new FirebaseHandler.SignupCallBack(){
                    @Override
                    public void onSignup(boolean res) {
                        if(res){
                            Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "User registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}