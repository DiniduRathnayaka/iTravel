package com.example.itravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HotelsAdd extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    EditText txtHotelName, txtHotelCountry, txtHotelDescription, txtHotelLink;
    Button btnAddHotel;
    DatabaseReference dbRef;
    Hotel hotel;
    long maxID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels_add);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        txtHotelName = findViewById(R.id.fillHotelNameAdd);
        txtHotelCountry = findViewById(R.id.fillCountryAddH);
        txtHotelDescription = findViewById(R.id.fillDescriptionAddH);
        txtHotelLink = findViewById(R.id.fillLocationLinkAddH);

        btnAddHotel = findViewById(R.id.addHotelBtn);

        hotel = new Hotel();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Hotel");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxID = dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnAddHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   dbRef = FirebaseDatabase.getInstance().getReference().child("Hotel");

                try {
                    if (TextUtils.isEmpty(txtHotelName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtHotelCountry.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Country", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtHotelDescription.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a Description", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtHotelLink.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a Link", Toast.LENGTH_SHORT).show();
                    else {
                        hotel.setHotelName(txtHotelName.getText().toString().trim());
                        hotel.setHotelCountry(txtHotelCountry.getText().toString().trim());
                        hotel.setHotelDescription(txtHotelDescription.getText().toString().trim());
                        hotel.setHotelLink(txtHotelLink.getText().toString().trim());

                       // dbRef.child("Hotel1").setValue(hotel);
                        dbRef.child(String.valueOf(maxID + 1)).setValue(hotel);

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearControls() {
        txtHotelName.setText("");
        txtHotelCountry.setText("");
        txtHotelDescription.setText("");
        txtHotelLink.setText("");
    }
}