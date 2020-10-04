package com.example.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.itravel.R;
import com.example.profile_upgrade.Viewpaymentlogs;
import com.example.profile_upgrade.profile_upgrade_main;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;

public class dashboard extends AppCompatActivity implements OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    ImageButton button1;
    ImageButton button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        button1 = (ImageButton) findViewById(R.id.Profile_btn);
        button2 = (ImageButton) findViewById(R.id.Feedback_btn);

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i=new Intent(dashboard.this, profile_upgrade_main.class);
                startActivity(i);
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent j=new Intent(dashboard.this, Viewpaymentlogs.class);
                startActivity(j);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.drawer_menu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menu_home ){
            Toast.makeText(this, "Loading Home", Toast.LENGTH_SHORT).show();
            Intent k = new Intent(dashboard.this, dashboard.class);
            startActivity(k);
        }
        else if (item.getItemId() == R.id.menu_locations ){
            Toast.makeText(this, "Loading Locations", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_hotels ){
            Toast.makeText(this, "Loading Hotels", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_favourites ){
            Toast.makeText(this, "Loading Favourites", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_aboutus ){
            Toast.makeText(this, "Loading About Us", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_contactus ){
            Toast.makeText(this, "Loading Contact Us", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_upgrade ){
            Toast.makeText(this, "Loading Upgrade", Toast.LENGTH_SHORT).show();
            Intent l=new Intent(dashboard.this, profile_upgrade_main.class);
            startActivity(l);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}