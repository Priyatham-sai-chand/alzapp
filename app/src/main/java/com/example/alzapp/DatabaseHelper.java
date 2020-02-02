package com.example.alzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
/*******
Created on: 21/01/2020

 By: B.Priyatham sai chand


 ********/

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "User.db";
    private static final String TABLE_NAME = "tblUser";
    private static final int DATABASE_VERSION = 2;
    private Context context;

    DatabaseHelper(Context context){
        super (context, DATABASE_NAME,null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE GENDER (GENDER INTEGER PRIMARY KEY, VALUE VARCHAR(20));");
        db.execSQL("CREATE TABLE TBLUSERINFO (USERID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT,FIRSTNAME VARCHAR(20),LASTNAME VARCHAR(20), DOB TEXT,EMAIL TEXT, GENDER INTEGER,FOREIGN KEY(GENDER) REFERENCES GENDER(GENDER));");
        db.execSQL("CREATE TABLE TBLUSERNAME(ID INTEGER PRIMARY KEY, USERNAME TEXT,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS TBLUSERINFO;");
        onCreate(db);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from TBLUSERINFO ORDER BY USERID",null);
        return res;
    }

    public void insertUsername(String username ,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO TBLUSERNAME VALUES (username,password);");

    }


    public void insertData(String reg_username,String reg_password,String first_name,String Last_name,String dob, String email,int gender)    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO TBLUSERINFO VALUES (reg_username,reg_password,first_name,last_name,dob,email,gender);");

    }


    public Cursor selectData(String firstname)    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from TBLUSERINFO WHERE FIRSTNAME='" + firstname + "' ORDER BY ID",null);
        return res;
    }

}
