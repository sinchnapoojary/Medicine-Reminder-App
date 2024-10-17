package com.example.finaljmaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class doctor extends AppCompatActivity {
    Button btnUpdate, btnAdd, btnDelete, btnSearch;
    EditText editTextDoctorName, editTextPhoneNumber, editTextSpecialization;
    TextView textViewTitle;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        // Initialize EditTexts
        editTextDoctorName = findViewById(R.id.editTextText);
        editTextPhoneNumber = findViewById(R.id.editTextText5);
        editTextSpecialization = findViewById(R.id.editTextText6);

        // Initialize Buttons
        btnAdd = findViewById(R.id.btnadd);
        btnUpdate = findViewById(R.id.btnupdate);
        btnDelete = findViewById(R.id.btndelete);
        btnSearch = findViewById(R.id.btnsearch);

        // Initialize DBHelper
        DB = DBHelper.getInstance(this);

        // Set onClickListeners for the buttons
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorName = editTextDoctorName.getText().toString().trim();
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();
                String specialization = editTextSpecialization.getText().toString().trim();

                if (doctorName.isEmpty() || phoneNumber.isEmpty() || specialization.isEmpty()) {
                    Toast.makeText(doctor.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean insert = DB.insertDoctorData(doctorName, phoneNumber, specialization);
                if (insert) {
                    Toast.makeText(doctor.this, "Doctor Added Successfully", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(doctor.this, "Failed to Add Doctor", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorName = editTextDoctorName.getText().toString().trim();
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();
                String specialization = editTextSpecialization.getText().toString().trim();

                if (doctorName.isEmpty() || phoneNumber.isEmpty() || specialization.isEmpty()) {
                    Toast.makeText(doctor.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean update = DB.updateDoctorData(doctorName, phoneNumber, specialization);
                if (update) {
                    Toast.makeText(doctor.this, "Doctor Updated Successfully", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(doctor.this, "Failed to Update Doctor", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorName = editTextDoctorName.getText().toString().trim();

                if (doctorName.isEmpty()) {
                    Toast.makeText(doctor.this, "Please enter a doctor name to delete", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean delete = DB.deleteDoctorData(doctorName);
                if (delete) {
                    Toast.makeText(doctor.this, "Doctor Deleted Successfully", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(doctor.this, "Failed to Delete Doctor", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorName = editTextDoctorName.getText().toString().trim();

                if (doctorName.isEmpty()) {
                    Toast.makeText(doctor.this, "Please enter a doctor name to search", Toast.LENGTH_SHORT).show();
                    return;
                }

                Cursor cursor = DB.getDoctorData(doctorName);
                if (cursor != null && cursor.moveToFirst()) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("Doctor Name: ").append(cursor.getString(0)).append("\n");
                    buffer.append("Phone Number: ").append(cursor.getString(1)).append("\n");
                    buffer.append("Specialization: ").append(cursor.getString(2)).append("\n\n");
                    Toast.makeText(doctor.this, buffer.toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(doctor.this, "No Doctor Found", Toast.LENGTH_SHORT).show();
                }

                if (cursor != null) {
                    cursor.close();
                }
            }
        });
    }

    // Method to clear input fields
    private void clearFields() {
        editTextDoctorName.setText("");
        editTextPhoneNumber.setText("");
        editTextSpecialization.setText("");
    }
}
