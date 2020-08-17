package com.example.itravel.RestaurentOffers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.itravel.R;

public class UpdateOffers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_offers);



        Spinner catDroupUp =(Spinner)findViewById(R.id.catDroupTxt);

        //create an ArrayAdapter using the string  array and a default  spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this,R.array.catDroupUp,android.R.layout.simple_spinner_item);

        //Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply the adapter to the spinner
        catDroupUp.setAdapter(staticAdapter);
    }
}