package com.example.finaljmaproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login1.db";
    private static DBHelper instance;

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        try {
            MyDB.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT)");
            MyDB.execSQL("CREATE TABLE Doctor(doctor_name TEXT PRIMARY KEY, phone_number TEXT, specialization TEXT)");
            MyDB.execSQL("CREATE TABLE Patient(patient_name TEXT PRIMARY KEY, address TEXT, medication TEXT, date_of_joining TEXT)");
        } catch (Exception e) {
            Log.e("DBHelper", "Error creating tables", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        try {
            MyDB.execSQL("DROP TABLE IF EXISTS users");
            MyDB.execSQL("DROP TABLE IF EXISTS doctor");
            MyDB.execSQL("DROP TABLE IF EXISTS patient");
            onCreate(MyDB);
        } catch (Exception e) {
            Log.e("DBHelper", "Error upgrading tables", e);
        }
    }

    // User-related methods
    public Boolean insertUserData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        MyDB.close();
        return result != -1;
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        MyDB.close();
        return exists;
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        MyDB.close();
        return exists;
    }

    // Doctor-related methods
    public Boolean insertDoctorData(String doctorName, String phoneNumber, String specialization) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("doctor_name", doctorName);
        contentValues.put("phone_number", phoneNumber);
        contentValues.put("specialization", specialization);
        long result = MyDB.insert("Doctor", null, contentValues);
        MyDB.close();
        return result != -1;
    }

    public Boolean updateDoctorData(String doctorName, String phoneNumber, String specialization) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone_number", phoneNumber);
        contentValues.put("specialization", specialization);
        long result = MyDB.update("Doctor", contentValues, "doctor_name = ?", new String[]{doctorName});
        MyDB.close();
        return result != -1;
    }

    public Boolean deleteDoctorData(String doctorName) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        long result = MyDB.delete("Doctor", "doctor_name = ?", new String[]{doctorName});
        MyDB.close();
        return result != -1;
    }

    public Cursor getDoctorData(String doctorName) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        return MyDB.rawQuery("Select * from Doctor where doctor_name = ?", new String[]{doctorName});
    }

    // Patient-related methods
    public Boolean insertPatientData(String patientName, String address, String medication, String dateOfJoining) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("patient_name", patientName);
        contentValues.put("address", address);
        contentValues.put("medication", medication);
        contentValues.put("date_of_joining", dateOfJoining);
        long result = MyDB.insert("Patient", null, contentValues);
        MyDB.close();
        return result != -1;
    }

    public Boolean updatePatientData(String patientName, String address, String medication, String dateOfJoining) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", address);
        contentValues.put("medication", medication);
        contentValues.put("date_of_joining", dateOfJoining);
        long result = MyDB.update("Patient", contentValues, "patient_name = ?", new String[]{patientName});
        MyDB.close();
        return result != -1;
    }

    public Boolean deletePatientData(String patientName) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        long result = MyDB.delete("Patient", "patient_name = ?", new String[]{patientName});
        MyDB.close();
        return result != -1;
    }

    public Cursor getPatientData() {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        return MyDB.rawQuery("Select * from Patient", null);
    }

    // Retrieve patient data by specific criteria, e.g., by name
    public Cursor getPatientDataByName(String patientName) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        return MyDB.rawQuery("Select * from Patient where patient_name = ?", new String[]{patientName});
    }
}
