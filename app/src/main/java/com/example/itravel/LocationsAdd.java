package com.example.itravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class LocationsAdd extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    EditText txtLocationName, txtLocationCountry, txtLocationDescription, txtLocationLink;
    Button btnAddLocation, btnUploadLocationImage;
    DatabaseReference dbRef;
    Location location;
//  StorageReference stRef;

//   Uri filePath;
//   ImageView imageView;
//    long maxID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_add);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        txtLocationName = findViewById(R.id.fillLocationNameAdd);
        txtLocationCountry = findViewById(R.id.fillCountryAdd);
        txtLocationDescription = findViewById(R.id.fillDescriptionAdd);
        txtLocationLink = findViewById(R.id.fillLocationLinkAdd);

      //  imageView = findViewById(R.id.image);
        btnAddLocation = findViewById(R.id.AddLocationBtn);
    //    btnUploadLocationImage = findViewById(R.id.imageUploadAdd);

        location = new Location();

     //   stRef = FirebaseStorage.getInstance().getReference("Location");
        dbRef = FirebaseDatabase.getInstance().getReference().child("Location");

/*        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
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
*/
      //  btnUploadLocationImage.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View view) {
      //          Filechooser();
      //      }
      //  });

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
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearControls() {
        txtLocationName.setText("");
        txtLocationCountry.setText("");
        txtLocationDescription.setText("");
        txtLocationLink.setText("");
    }

   // private void Filechooser() {
   //     Intent intent = new Intent();
   //     Intent.setType
   // }
}