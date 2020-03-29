package com.example.alzapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class TileSender extends StringRequest {
    private static final String REQUEST_URL = "https://eclectic-sweeps.000webhostapp.com/tile_sender.php";
    private Map<String, String> params;

    TileSender(String username,long times , Response.Listener<String> listener) {
        super(Method.POST, REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("tile",times+"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }



}
