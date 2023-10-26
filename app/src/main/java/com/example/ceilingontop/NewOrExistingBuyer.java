package com.example.ceilingontop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewOrExistingBuyer extends AppCompatActivity {

    Button button_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_or_existing_buyer);

        CardView newBuyer = findViewById(R.id.cv_newbuyer);
        CardView existingBuyer = findViewById(R.id.cv_existingbuyer);
        button_back = findViewById(R.id.btn_back6);

        newBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewOrExistingBuyer.this, ActivityRegistrationUser.class);
                intent.putExtra("source", "buyer");
                startActivity(intent);
            }
        });

        existingBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewOrExistingBuyer.this, ActivityLoginUser.class);
                intent.putExtra("source", "buyer");
                startActivity(intent);
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewOrExistingBuyer.this, ActivityBuyerorSeller.class);
                startActivity(intent);
            }
        });
    }
}