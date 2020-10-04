package com.example.itravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
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

public class LocationsUpdate extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    EditText txtLocationName, txtLocationCountry, txtLocationDescription, txtLocationLink;
    Button btnUpdateLocation, btnUploadLocationImage;
    DatabaseReference dbRef;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_update);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        txtLocationName = findViewById(R.id.fillLocationNameUpdate);
        txtLocationCountry = findViewById(R.id.fillCountryUpdate);
        txtLocationDescription = findViewById(R.id.fillDescriptionUpdate);
        txtLocationLink = findViewById(R.id.fillLocationLinkUpdate);

        btnUpdateLocation = findViewById(R.id.updateLocationBtn);

        btnUpdateLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Location");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Location1")) {
                            try {
                                location.setLocationName(txtLocationName.getText().toString().trim());
                                location.setLocationCountry(txtLocationCountry.getText().toString().trim());
                                location.setLocationDescription(txtLocationDescription.getText().toString().trim());
                                location.setLocationLink(txtLocationLink.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Location").child("Location1");
                                dbRef.setValue(location);
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
    }

    private void clearControls() {
        txtLocationName.setText("");
        txtLocationCountry.setText("");
        txtLocationDescription.setText("");
        txtLocationLink.setText("");
    }
}