package com.example.profile_upgrade;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.Home.dashboard;
import com.example.itravel.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.Timestamp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class profile_upgrade_main_pre_pay_details extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    ImageButton button1;
    EditText cardName;
    EditText cardNo;
    EditText expMonth;
    EditText expYear;
    EditText cardCVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_upgrade_main_pre_pay_details);

        final DatabaseReference myRef;
        myRef = FirebaseDatabase.getInstance().getReference("Financial");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        button1 = (ImageButton) findViewById(R.id.next2);
        cardName = (EditText) findViewById(R.id.nameOnCard);
        cardNo = (EditText) findViewById(R.id.cardNo);
        expMonth = (EditText) findViewById(R.id.expMonth);
        expYear = (EditText) findViewById(R.id.expYear);
        cardCVV = (EditText) findViewById(R.id.cardCVV);

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String cardname = cardName.getText().toString();
                String cardno = cardNo.getText().toString();
                String expmonth = expMonth.getText().toString();
                String expyear = expYear.getText().toString();
                String cardcvv = cardCVV.getText().toString();

                Intent i2 = getIntent();
                String str = i2.getStringExtra("method");

                String key = myRef.push().getKey();

                myRef.child(key).child("Id").setValue(key);
                myRef.child(key).child("Name on Card").setValue(cardname);
                myRef.child(key).child("Card Number").setValue(cardno);
                myRef.child(key).child("Card EXP Month").setValue(expmonth);
                myRef.child(key).child("Card EXP Year").setValue(expyear);
                myRef.child(key).child("CVV").setValue(cardcvv);
                myRef.child(key).child("Payment method").setValue(str);

                Intent i=new Intent(profile_upgrade_main_pre_pay_details.this, Payment_editable_recipt.class);
                startActivity(i);
            }
        });
    }
}