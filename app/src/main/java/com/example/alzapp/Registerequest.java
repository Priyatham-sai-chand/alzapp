package com.example.alzapp;

import com.android.volley.Response;
import com.android.volley.toolbox.*;

import java.util.HashMap;
import java.util.Map;

public class Registerequest extends StringRequest {
    private static final String REQUEST_URL = "https://eclectic-sweeps.000webhostapp.com/registration_enc.php";
    private Map<String, String> params;

    Registerequest(String firstname, String lastname, String username, String dob, String email, String password, String gender, int age, Response.Listener<String> listener) {
        super(Method.POST, REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("firstname", firstname);
        params.put("lastname", lastname);
        params.put("dob",dob);
        params.put("email",email);
        params.put("gender",gender);
        params.put("age",age+"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
