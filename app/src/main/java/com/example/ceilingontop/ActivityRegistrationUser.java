package com.example.ceilingontop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityRegistrationUser extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText email, password, name, phone;
    private Button button, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_user);


        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.id_email_newuser);
        password = findViewById(R.id.id_password_newuser);
        name = findViewById(R.id.id_fullname_newuser);
        phone = findViewById(R.id.id_mobileno_newuser);
        button = findViewById(R.id.btn_regitser);
        back = findViewById(R.id.btn_back4);

        Intent path = getIntent();
        String source = path.getStringExtra("source");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString();
                String pass = password.getText().toString();
                String fullname = name.getText().toString();
                String mobileno = phone.getText().toString();
                if (fullname.isEmpty()){
                    name.setError("Please enter your name");
                }
                if (user.isEmpty()){
                    email.setError("Please enter email id");
                }
                if (pass.isEmpty()){
                    password.setError("Please enter password");
                }
                if (mobileno.isEmpty()){
                    phone.setError("Please enter mobile number");
                }
                else {
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                if (source.equals("seller")){
                                    Intent intent = new Intent(ActivityRegistrationUser.this, NewOrExistingSeller.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else if(source.equals("buyer")){
                                    Intent intent = new Intent(ActivityRegistrationUser.this, NewOrExistingBuyer.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            else {
                                Toast toast = Toast.makeText(ActivityRegistrationUser.this, "Registration Failed", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                        }
                    });
                }

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegistrationUser.this, NewOrExistingSeller.class);
                startActivity(intent);
            }
        });









    }
}