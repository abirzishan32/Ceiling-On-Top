package com.example.ceilingontop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityBeforeUploadHouse extends AppCompatActivity {
    Button upload, back;
    TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_upload_house);
        upload = findViewById(R.id.btn_lets_upload);
        back = findViewById(R.id.btn_back);
        email = findViewById(R.id.set_email_id);



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityBeforeUploadHouse.this, ActivityUploadHouse.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityBeforeUploadHouse.this, ActivityLoginUser.class);
                startActivity(intent);
            }
        });

    }
}