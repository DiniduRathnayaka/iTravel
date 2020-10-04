package com.example.profile_upgrade;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itravel.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Payment_editable_recipt extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private EditText popup_name_on_card;
    private RadioGroup popup_method;
    private EditText popup_card_number;
    private EditText popup_card_EXP_Month;
    private EditText popup_card_EXP_Year;
    private EditText popup_card_CVV;
    private RadioButton radioButton;
    private Button popup_update;
    private Button popup_cancel;

    ImageButton button1;
    Button button2;
    TextView cardname;
    TextView cardnumber;
    TextView cardexp;
    TextView cvvno;
    TextView datetxt;
    TextView methodtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_editable_recipt);

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

        button1 = (ImageButton) findViewById(R.id.nextdone);
        button2 = (Button) findViewById(R.id.updatebtn);
        cardname = (TextView) findViewById(R.id.cardname);
        cardnumber = (TextView) findViewById(R.id.cardno);
        cardexp = (TextView) findViewById(R.id.cardexp);
        cvvno = (TextView) findViewById(R.id.cvv);
        datetxt = (TextView) findViewById(R.id.datetext);
        methodtxt = (TextView) findViewById(R.id.payMethod);

        myRef.addChildEventListener(new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String cvvNo = (String) snapshot.child("CVV").getValue();
                String expMth = (String) snapshot.child("Card EXP Month").getValue();
                String expYr = (String) snapshot.child("Card EXP Year").getValue();
                String cardNo = (String) snapshot.child("Card Number").getValue();
                String nameoncard = (String) snapshot.child("Name on Card").getValue();
                String method = (String) snapshot.child("Payment method").getValue();

                Log.v("E_VALUE","Data : " + method);

                cardname.setText(nameoncard);
                cardnumber.setText(cardNo);
                cardexp.setText(expYr + "/" + expMth);
                cvvno.setText(cvvNo);
                methodtxt.setText(method);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(Payment_editable_recipt.this, profile_upgrade_main_success.class);
                startActivity(i);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewContactDialog();
            }
        });
    }

    public void createNewContactDialog(){
        final DatabaseReference myRef;
        myRef = FirebaseDatabase.getInstance().getReference("Financial");
        final String FinancialId = myRef.getKey();

        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.activity_update_popup,null);

        popup_name_on_card = (EditText) contactPopupView.findViewById(R.id.nameOnCard);
        popup_method = (RadioGroup) contactPopupView.findViewById(R.id.payMethod);
        popup_card_number = (EditText) contactPopupView.findViewById(R.id.cardNo);
        popup_card_EXP_Month = (EditText) contactPopupView.findViewById(R.id.expMonth);
        popup_card_EXP_Year = (EditText) contactPopupView.findViewById(R.id.expYear);
        popup_card_CVV = (EditText) contactPopupView.findViewById(R.id.cardCvv);

        popup_update = (Button) contactPopupView.findViewById(R.id.popUpdate);
        popup_cancel = (Button) contactPopupView.findViewById(R.id.popClose);

        myRef.addChildEventListener(new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String cvvNo = (String) snapshot.child("CVV").getValue();
                String expMth = (String) snapshot.child("Card EXP Month").getValue();
                String expYr = (String) snapshot.child("Card EXP Year").getValue();
                String cardNo = (String) snapshot.child("Card Number").getValue();
                String nameoncard = (String) snapshot.child("Name on Card").getValue();
                String method = (String) snapshot.child("Payment method").getValue();

                popup_name_on_card.setText(nameoncard);
                popup_card_number.setText(cardNo);
                popup_card_EXP_Month.setText(expMth);
                popup_card_EXP_Year.setText(expYr);
                popup_card_CVV.setText(cvvNo);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        popup_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardname = popup_name_on_card.getText().toString();
                String cardno = popup_card_number.getText().toString();
                String expmonth = popup_card_EXP_Month.getText().toString();
                String expyear = popup_card_EXP_Year.getText().toString();
                String cardcvv = popup_card_CVV.getText().toString();
                int selectedPayId = popup_method.getCheckedRadioButtonId();
                radioButton = (RadioButton) contactPopupView.findViewById(selectedPayId);
                String payMethod = (String) radioButton.getText();

                myRef.child(FinancialId).child("Name on Card").setValue(cardname);
                myRef.child(FinancialId).child("Card Number").setValue(cardno);
                myRef.child(FinancialId).child("Card EXP Month").setValue(expmonth);
                myRef.child(FinancialId).child("Card EXP Year").setValue(expyear);
                myRef.child(FinancialId).child("CVV").setValue(cardcvv);
                myRef.child(FinancialId).child("Id").setValue(FinancialId);
                myRef.child(FinancialId).child("Payment method").setValue(payMethod);

                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();

                Intent i=new Intent(Payment_editable_recipt.this, Payment_editable_recipt.class);
                startActivity(i);
            }
        });

        popup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}