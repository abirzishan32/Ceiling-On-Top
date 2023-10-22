package com.example.ceilingontop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewOrExistingBuyer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_or_existing_buyer);

        CardView newBuyer = findViewById(R.id.cv_newbuyer);
        CardView existingBuyer = findViewById(R.id.cv_existingbuyer);

        newBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewOrExistingBuyer.this, ActivityRegistrationUser.class);
                startActivity(intent);
            }
        });

        existingBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewOrExistingBuyer.this, ActivityLoginUser.class);
                startActivity(intent);
            }
        });
    }
}