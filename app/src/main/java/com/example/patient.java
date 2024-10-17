package com.example.finaljmaproject;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class patient extends AppCompatActivity {
    EditText editTextPatientName, editTextAddress, editTextMedication, editTextDateOfJoining;
    Button btnAdd, btnUpdate, btnDelete, btnSearch;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

                editTextPatientName = findViewById(R.id.editTextText2);
                editTextAddress = findViewById(R.id.editTextText4);
                editTextMedication = findViewById(R.id.editTextText5);
                editTextDateOfJoining = findViewById(R.id.editTextText3);
                btnAdd = findViewById(R.id.btnadd1);
                btnUpdate = findViewById(R.id.btnupdate1);
                btnDelete = findViewById(R.id.btndelete1);
                btnSearch = findViewById(R.id.btnsearch1);
                DB= DBHelper.getInstance(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientName = editTextPatientName.getText().toString();
                String address = editTextAddress.getText().toString();
                String medication = editTextMedication.getText().toString();
                String dateOfJoining = editTextDateOfJoining.getText().toString();

                Boolean insert = DB.insertPatientData(patientName, address, medication, dateOfJoining);
                if (insert) {
                    Toast.makeText(patient.this, "Patient Added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(patient.this, "Patient Add Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientName = editTextPatientName.getText().toString();
                String address = editTextAddress.getText().toString();
                String medication = editTextMedication.getText().toString();
                String dateOfJoining = editTextDateOfJoining.getText().toString();

                Boolean update = DB.updatePatientData(patientName, address, medication, dateOfJoining);
                if (update) {
                    Toast.makeText(patient.this, "Patient Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(patient.this, "Patient Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientName = editTextPatientName.getText().toString();
                Boolean delete = DB.deletePatientData(patientName);
                if (delete) {
                    Toast.makeText(patient.this, "Patient Deleted Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(patient.this, "Patient Delete Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientName = editTextPatientName.getText().toString();
                Cursor cursor = DB.getPatientDataByName(patientName);
                if (cursor != null && cursor.moveToFirst()) {
                    editTextAddress.setText(cursor.getString(1));  // Assuming address is the second column
                    editTextMedication.setText(cursor.getString(2));  // Assuming medication is the third column
                    editTextDateOfJoining.setText(cursor.getString(3));  // Assuming date_of_joining is the fourth column
                    Toast.makeText(patient.this, "Patient Found", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(patient.this, "Patient Not Found", Toast.LENGTH_SHORT).show();
                }
                if (cursor != null) {
                    cursor.close();
                }
            }
        });
    }
    // This closing brace ends the onCreate method
}  // This closing brace ends the PatientActivity class
