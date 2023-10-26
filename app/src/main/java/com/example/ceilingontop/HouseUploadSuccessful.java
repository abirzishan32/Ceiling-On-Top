package com.example.ceilingontop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HouseUploadSuccessful extends AppCompatActivity {
    Button btn_continue, btn_logout;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_upload_successful);
        btn_continue = findViewById(R.id.btn_continue);
        btn_logout = findViewById(R.id.btn_log_out);
        firebaseAuth = FirebaseAuth.getInstance();
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HouseUploadSuccessful.this, ActivityBeforeUploadHouse.class);
                startActivity(intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Logged Out Successfully", Toast.LENGTH_SHORT);
                firebaseAuth.signOut();
                Intent intent = new Intent(HouseUploadSuccessful.this, ActivityBuyerorSeller.class);
                startActivity(intent);
                finish();
            }
        });
    }
}