package com.example.ceilingontop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLoginUser extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText email, password;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email_et);
        password = findViewById(R.id.password_et);
        button = findViewById(R.id.btn_sign_in);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString();
                String pass = password.getText().toString();
                if(!user.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(user).matches()){
                    if(!pass.isEmpty()){
                        auth.signInWithEmailAndPassword(user, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast toast = Toast.makeText(ActivityLoginUser.this, "Login Successful", Toast.LENGTH_SHORT);
                                toast.show();
                                startActivity(new Intent(ActivityLoginUser.this, ActivityBeforeUploadHouse.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast toast = Toast.makeText(ActivityLoginUser.this, "Login Failed", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });
                        }
                    else {
                        password.setError("Please enter password");
                    }
                    }
                else if(user.isEmpty()){
                    email.setError("Please enter email id");
                }
                else {
                    email.setError("Please enter valid email id");
                }

            }
        });

    }
}