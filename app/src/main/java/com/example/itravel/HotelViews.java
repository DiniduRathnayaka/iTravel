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

public class HotelViews extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    EditText txtHotelName, txtHotelCountry, txtHotelDescription, txtHotelLink;
    Button btnAddHotel, btnUpdateHotel, btnDeleteHotel, btnShowHotel;
    DatabaseReference dbRef;
    Hotel hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_views);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        txtHotelName = findViewById(R.id.VHN);
        txtHotelCountry = findViewById(R.id.VHC);
        txtHotelDescription = findViewById(R.id.VHD);
        txtHotelLink = findViewById(R.id.VHL);

        btnAddHotel = findViewById(R.id.addHBtn);
        btnShowHotel = findViewById(R.id.showHBtn);
        btnUpdateHotel = findViewById(R.id.updateHBtn);
        btnDeleteHotel = findViewById(R.id.deleteHBtn);

        hotel = new Hotel();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Hotel");

        btnAddHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Hotel");

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

                        dbRef.child("Hotel1").setValue(hotel);
                        //dbRef.child(String.valueOf(maxID + 1)).setValue(location);

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NullPointerException e) {
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShowHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Hotel/Hotel1");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            txtHotelName.setText(dataSnapshot.child("hotelName").getValue().toString());
                            txtHotelCountry.setText(dataSnapshot.child("hotelCountry").getValue().toString());
                            txtHotelDescription.setText(dataSnapshot.child("hotelDescription").getValue().toString());
                            txtHotelLink.setText(dataSnapshot.child("hotelLink").getValue().toString());
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnUpdateHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Hotel");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Hotel1")) {
                            try {
                                hotel.setHotelName(txtHotelName.getText().toString().trim());
                                hotel.setHotelCountry(txtHotelCountry.getText().toString().trim());
                                hotel.setHotelDescription(txtHotelDescription.getText().toString().trim());
                                hotel.setHotelLink(txtHotelLink.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Hotel").child("Hotel1");
                                dbRef.setValue(hotel);
                                clearControls();

                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnDeleteHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Hotel");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Hotel1")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Hotel").child("Hotel1");
                            dbRef.removeValue();
                            clearControls();

                            Toast.makeText(getApplicationContext(), "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Delete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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