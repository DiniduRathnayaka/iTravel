package com.example.itravel.RestaurentOffers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.itravel.R;

public class AddOffers extends AppCompatActivity {

    Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offers);

        addbtn=(Button) findViewById(R.id.addOffBtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddOffers.this,Hotels.class);
                startActivity(intent);
            }
        });


        Spinner spinner =(Spinner)findViewById(R.id.spinner1);

        //create an ArrayAdapter using the string  array and a default  spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this,R.array.spinner,android.R.layout.simple_spinner_item);

        //Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply the adapter to the spinner
        spinner.setAdapter(staticAdapter);



    }
}