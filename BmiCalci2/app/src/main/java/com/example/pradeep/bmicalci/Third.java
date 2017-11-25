package com.example.pradeep.bmicalci;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.name;
import static com.example.pradeep.bmicalci.R.id.etAge;
import static com.example.pradeep.bmicalci.R.id.etName;
import static com.example.pradeep.bmicalci.R.id.etPhoneNumber;
import static com.example.pradeep.bmicalci.R.id.tvName;

public class Third extends AppCompatActivity {

    TextView tvUnderweight,tvNormal,tvOverweight,tvobese;
    Button btnSave,btnShare,btnBack,btnSee;
    SharedPreferences sp1;
    String extra= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        int orientation= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);


        tvUnderweight = (TextView) findViewById(R.id.tvUnderweight);
        tvNormal = (TextView) findViewById(R.id.tvNormal);
        tvOverweight = (TextView) findViewById(R.id.tvOverweight);
        tvobese = (TextView) findViewById(R.id.tvObese);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnShare = (Button) findViewById(R.id.btnShare);
        btnSee= (Button) findViewById(R.id.btnSee);


        final Database db= new Database(this);


        Intent i = getIntent();
final double bmi = i.getDoubleExtra("bmi", 0);
        if (bmi < 18.5) {
        extra = extra + " you are underweight ";
        } else if (18.5 < bmi && bmi < 25) {
        extra = extra + " you are normal ";
        } else if (25 < bmi && bmi < 30) {
        extra = extra + " you are overweight ";
        } else if (bmi > 30) {
        extra = extra + " you are obese ";
        }


        btnSee.setText("Your BMI is " + "\n" + bmi + " and " + extra);
        btnSee.setTextColor(Color.parseColor("#00008B"));

        if (bmi < 18.5) {
        tvUnderweight.setTextColor(Color.parseColor(getString(R.string.red)));

        } else if (18.5 < bmi && bmi < 25) {
        tvNormal.setTextColor(Color.parseColor(getString(R.string.red)));

        } else if (25 < bmi && bmi < 30) {
        tvOverweight.setTextColor(Color.parseColor(getString(R.string.red)));
        } else if (bmi > 30) {
        tvobese.setTextColor(Color.parseColor(getString(R.string.red)));
        }
        sp1= getSharedPreferences("myp1",MODE_PRIVATE);
        final String n= sp1.getString("n","");

        final String a= sp1.getString("a","");
        final String p= sp1.getString("p","");







        btnShare.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {



        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, " Name :" + n + "\n" + "Age :" + a + "\n" +" Phone no :" + p + "\n" + " BMI  "  + bmi);
        startActivity(i);

        }
        });

           btnBack.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
           Intent i = new Intent(Third.this, Second.class);
           startActivity(i);
            finish();

        }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.addData(n,a,p,bmi);
            }
        });



        }

        }
