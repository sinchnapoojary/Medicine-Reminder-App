package com.example.finaljmaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button Button2,btnpatient,btndoctor;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button2=(Button)findViewById(R.id.button2);
        btnpatient=(Button)findViewById(R.id.btnpatient);
        btndoctor=(Button)findViewById(R.id.btndoctor);
        btnpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient1();
            }
        });

        btndoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctor1();
            }
        });

        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity2();
            }
        });
    }
    public void Activity2(){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    public void patient1(){
        Intent intent=new Intent(this, patient.class);
        startActivity(intent);


    }
    public void doctor1(){
        Intent intent=new Intent(this,doctor.class);
        startActivity(intent);
    }


}