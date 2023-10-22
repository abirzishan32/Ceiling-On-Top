package com.example.ceilingontop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityBuyerorSeller extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyeror_seller);

        CardView cv_buyer = findViewById(R.id.cv_buyer);
        CardView cv_seller = findViewById(R.id.cv_seller);

        cv_seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityBuyerorSeller.this, NewOrExistingSeller.class);
                startActivity(intent);
            }
        });

        cv_buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityBuyerorSeller.this, NewOrExistingBuyer.class);
                startActivity(intent);
            }
        });
    }
}