package com.example.profile_upgrade;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.Home.dashboard;
import com.example.itravel.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile_upgrade_main_pre_pay_method extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    ImageButton button1;
    RadioGroup method;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_upgrade_main_pre_pay_method);
// ...
//        final DatabaseReference myRef;
//        myRef = FirebaseDatabase.getInstance().getReference("Financial");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        button1 = (ImageButton) findViewById(R.id.next1);
        method = (RadioGroup) findViewById(R.id.payMethod);

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                int selectedPayId = method.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedPayId);

                String payMethod = (String) radioButton.getText();
//                String key = myRef.push().getKey();
//
//                myRef.child(key).child("Payment Method").setValue(payMethod);


                Intent i=new Intent(profile_upgrade_main_pre_pay_method.this, profile_upgrade_main_pre_pay_details.class);
                i.putExtra("method",payMethod);
                startActivity(i);
            }
        });
    }
}