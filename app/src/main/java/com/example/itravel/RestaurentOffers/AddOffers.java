package com.example.itravel.RestaurentOffers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.itravel.R;

public class AddOffers extends AppCompatActivity {

    Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offers);

        addbtn=(Button) findViewById(R.id.addOffer);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddOffers.this,Hotels.class);
                startActivity(intent);
            }
        });

    }
}