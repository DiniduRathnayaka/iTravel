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

public class LocationsView extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    EditText txtLocationName, txtLocationCountry, txtLocationDescription, txtLocationLink;
    Button btnAddLocation, btnUpdateLocation, btnDeleteLocation, btnShowLocation;
    DatabaseReference dbRef;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_view);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        txtLocationName = findViewById(R.id.VLN);
        txtLocationCountry = findViewById(R.id.VLC);
        txtLocationDescription = findViewById(R.id.VLD);
        txtLocationLink = findViewById(R.id.VLL);

        btnAddLocation = findViewById(R.id.addBtn);
        btnShowLocation = findViewById(R.id.showBtn);
        btnUpdateLocation = findViewById(R.id.updateBtn);
        btnDeleteLocation = findViewById(R.id.deleteBtn);

        location = new Location();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Location");

        btnAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Location");

                try {
                    if (TextUtils.isEmpty(txtLocationName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtLocationCountry.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter Country", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtLocationDescription.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a Description", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtLocationLink.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a Link", Toast.LENGTH_SHORT).show();
                    else {
                        location.setLocationName(txtLocationName.getText().toString().trim());
                        location.setLocationCountry(txtLocationCountry.getText().toString().trim());
                        location.setLocationDescription(txtLocationDescription.getText().toString().trim());
                        location.setLocationLink(txtLocationLink.getText().toString().trim());

                        dbRef.child("Location1").setValue(location);
                        //dbRef.child(String.valueOf(maxID + 1)).setValue(location);

                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NullPointerException e) {
                    Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Location/Location1");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            txtLocationName.setText(dataSnapshot.child("locationName").getValue().toString());
                            txtLocationCountry.setText(dataSnapshot.child("locationCountry").getValue().toString());
                            txtLocationDescription.setText(dataSnapshot.child("locationDescription").getValue().toString());
                            txtLocationLink.setText(dataSnapshot.child("locationLink").getValue().toString());
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

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

        btnDeleteLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Location");
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Location1")) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Location").child("Location1");
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
        txtLocationName.setText("");
        txtLocationCountry.setText("");
        txtLocationDescription.setText("");
        txtLocationLink.setText("");
    }

}