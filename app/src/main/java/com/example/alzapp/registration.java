package com.example.alzapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
/*******
 Created on: 21/01/2020

 By: B.Priyatham sai chand


 ********/

public class registration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView signin;
    private TextView dob;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private EditText username;
    private EditText password;
    private EditText firstname;
    private EditText lastname;
    private EditText email_id;
    private Button signup;
     String ages;

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

        Button back =  findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // get selected radio button from radioGroup
        int selectedId = radioSexGroup.getCheckedRadioButtonId();

        //find the radiobutton by returned id
        radioSexButton = findViewById(selectedId);



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
        String currentDateString = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        LocalDate l1 = LocalDate.of(year, month, dayOfMonth);

        age(currentDateString,l1);




    }

    public void openActivity() {

        Intent intent = new Intent(this,login.class);
        startActivity(intent);



    }


    public void checkButton(View v) {
        int radioId = radioSexGroup.getCheckedRadioButtonId();

        radioSexButton = findViewById(radioId);


    }
    public void age(String s,LocalDate l1)
    {
        try {


            DateFormat originalFormat = new SimpleDateFormat("dd MMM yyyy", Locale.US);
            DateFormat targetFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
            Date date = originalFormat.parse(s);
            if (date != null) {
                String formattedDate = targetFormat.format(date);
            }

            LocalDate now1 = LocalDate.now();
            Period diff1 = Period.between(l1, now1);
            int age = diff1.getYears();
            ages = String.valueOf(age);





        }
        catch(ParseException e)
        {
            e.printStackTrace();

        }


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
