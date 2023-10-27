package com.example.ceilingontop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.ceilingontop.HouseListing;

public class ActivityLoginUser extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText email, password;
    private Button button;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email_et);
        password = findViewById(R.id.password_et);
        button = findViewById(R.id.btn_sign_in);
        back = findViewById(R.id.btn_back2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString();
                String pass = password.getText().toString();

                Intent path = getIntent();
                String source = path.getStringExtra("source");


                if(!user.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(user).matches()){
                    if(!pass.isEmpty()){
                        auth.signInWithEmailAndPassword(user, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast toast = Toast.makeText(ActivityLoginUser.this, "Login Successful", Toast.LENGTH_SHORT);
                                toast.show();
                                if (source.equals("seller")){
                                    Intent intent = new Intent(ActivityLoginUser.this, ActivityBeforeUploadHouse.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else if(source.equals("buyer")) {
                                    Intent intent = new Intent(ActivityLoginUser.this, HouseListing.class);
                                    startActivity(intent);
                                    finish();
                                }
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






        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLoginUser.this, NewOrExistingSeller.class);
                startActivity(intent);
            }
        });

    }
}