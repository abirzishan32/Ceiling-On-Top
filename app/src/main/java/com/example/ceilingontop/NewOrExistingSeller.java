package com.example.ceilingontop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewOrExistingSeller extends AppCompatActivity {

    Button button_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_or_existing_seller);

        CardView newSeller = findViewById(R.id.cv_newseller);
        CardView existingSeller = findViewById(R.id.cv_existingseller);
        button_back = findViewById(R.id.btn_back5);

        newSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewOrExistingSeller.this, ActivityRegistrationUser.class);
                intent.putExtra("source", "seller");
                startActivity(intent);
            }
        });

        existingSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewOrExistingSeller.this, ActivityLoginUser.class);
                intent.putExtra("source", "seller");
                startActivity(intent);
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewOrExistingSeller.this, ActivityBuyerorSeller.class);
                startActivity(intent);
            }
        });

    }
}