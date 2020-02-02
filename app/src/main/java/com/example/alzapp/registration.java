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
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.ViewGroup;
import android.view.Gravity;
/*******
 Created on: 21/01/2020

 By: B.Priyatham sai chand


 ********/

public class registration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView signin;
    private TextView dob;
    com.example.alzapp.DatabaseHelper dal;
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
    int sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dal = new DatabaseHelper(registration.this);
        username = (EditText) findViewById(R.id.reg_useername);
        password = (EditText) findViewById(R.id.reg_password);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        email_id = (EditText) findViewById(R.id.email_id);
        signup = (Button) findViewById(R.id.signup_button);
        signin = (TextView) findViewById(R.id.signIn_text);
        dob = (TextView) findViewById(R.id.dob1);
        signup_button = (Button) findViewById(R.id.signup_button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }


        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isEmpty(username) || isEmpty(password) || isEmpty(firstname) || isEmpty(lastname) || isEmpty(email_id)){

                    Toast.makeText(registration.this, "One or more fields empty.", Toast.LENGTH_SHORT).show();



                }

                else {



                    insertData();

                }
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

    public void addListenerOnButton() {

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);






                // get selected radio button from radioGroup
                int selectedId = radioSexGroup.getCheckedRadioButtonId();

                //find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);

                if(radioSexButton.getText() == "Male"){
                    sex = 0;
                }
                else if (radioSexButton.getText() == "Female"){

                    sex = 1;
                }
                else{

                    sex = -1;
                }






    }
    public void insertData(){

        dal.insertData(username.getText().toString(),lastname.getText().toString(),firstname.getText().toString(),lastname.getText().toString(),dob.getText().toString(),email_id.getText().toString(),sex);


    }
    public boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public void showToast() {
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
    }

}
