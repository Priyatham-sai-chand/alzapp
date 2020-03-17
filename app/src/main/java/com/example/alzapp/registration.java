package com.example.alzapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.RadioGroup;
import java.text.DateFormat;
import java.util.Calendar;
import android.view.LayoutInflater;
import android.widget.ImageView;

import android.widget.Toast;

import android.view.ViewGroup;
import android.view.Gravity;
import org.json.*;
import java.lang.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import android.app.AlertDialog;
/*******
 Created on: 21/01/2020

 By: B.Priyatham sai chand


 ********/

public class registration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView signin;
    private TextView dob;

    SQLiteDatabase sqLiteDatabase;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private EditText username;
    private EditText password;
    private EditText firstname;
    private EditText lastname;
    private EditText email_id;
    private Button signup;
    private Button signup_button;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        username = findViewById(R.id.reg_username);
        password = findViewById(R.id.reg_password);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email_id = findViewById(R.id.email_id);
        signup = findViewById(R.id.signup_button);
        signin = findViewById(R.id.signIn_text);
        dob = findViewById(R.id.dob1);

        radioSexGroup =  findViewById(R.id.radioSex);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }


        });




        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });

        Button back = (Button) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // get selected radio button from radioGroup
        int selectedId = radioSexGroup.getCheckedRadioButtonId();

        //find the radiobutton by returned id
        radioSexButton = (RadioButton) findViewById(selectedId);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isEmpty(username) || isEmpty(password) || isEmpty(firstname) || isEmpty(lastname) || isEmpty(email_id)){

                    Toast.makeText(registration.this, "One or more fields empty.", Toast.LENGTH_SHORT).show();



                }

                else {

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    Intent intent = new Intent(registration.this,UserAreaActivity.class);
                                    startActivity(intent);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(registration.this);
                                    builder.setMessage("Register Failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    Registerequest reg_request = new Registerequest(firstname.getText().toString(),lastname.getText().toString(),username.getText().toString(),dob.getText().toString(),email_id.getText().toString(),password.getText().toString(),radioSexButton.getText().toString(),responseListener);
                    RequestQueue queue = Volley.newRequestQueue(registration.this);
                    queue.add(reg_request);





                }
            }
        });





    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.dob1);
        textView.setText(currentDateString);
    }

    public void openActivity() {

        Intent intent = new Intent(this,login.class);
        startActivity(intent);



    }


    public void checkButton(View v) {
        int radioId = radioSexGroup.getCheckedRadioButtonId();

        radioSexButton = findViewById(radioId);


    }





    public boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    /*public void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast_root));

        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);

        toastText.setText("One of the fields is empty Try again ");


        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }*/





}
