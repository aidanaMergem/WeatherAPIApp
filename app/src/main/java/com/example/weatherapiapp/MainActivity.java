package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btn_cityId, btn_getWeatherByID, btn_getWeatherByName;
    EditText et_dataInput;
    ListView lv_weatherReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign values to each control on the layout
        btn_cityId = findViewById(R.id.btn_getCityID);
        btn_getWeatherByID = findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);

        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReport = findViewById(R.id.lv_WeatherReports);

        //click listeners to each button
        btn_cityId.setOnClickListener(v -> {

// Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

            String url = "https://poetrydb.org//title/"+et_dataInput.getText().toString();

            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    String author = "";
                    try {
                        JSONObject poetryInfo = response.getJSONObject(0);
                        author = poetryInfo.getString("author");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    Toast.makeText(MainActivity.this,"Author: "+ author, Toast.LENGTH_LONG).show();
                }
            },

                    error -> {
                        Toast.makeText(MainActivity.this, "Error occurred!", Toast.LENGTH_LONG).show();
                    });

            queue.add(request);



//            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
//                    response -> Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show(),
//                    error -> Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_LONG).show()) {
//
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String> headers = new HashMap<>();
//                    headers.put("X-Api-Key", "7s27hslObwJ7MmmsVa9+uA==JIMBhviExZjDlJMC");
//                    return headers;
//                }
//            };
//
//            queue.add(request);

//// Request a string response from the provided URL.
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                    response -> Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show(), error -> Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_LONG).show());

// Add the request to the RequestQueue.

//                Toast.makeText(MainActivity.this, "You clicked me 1", Toast.LENGTH_LONG).show();
        });
        btn_getWeatherByID.setOnClickListener(v -> Toast.makeText(MainActivity.this, "You clicked me 2", Toast.LENGTH_LONG).show());
        btn_getWeatherByName.setOnClickListener(v -> Toast.makeText(MainActivity.this, "You typed " + et_dataInput.getText().toString(), Toast.LENGTH_LONG).show());
    }
}