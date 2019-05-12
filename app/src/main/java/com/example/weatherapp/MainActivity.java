package com.example.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText userCity;
    private TextView resultUser;
    private Button button;
    //https://api.openweathermap.org/data/2.5/weather?q=mumbai&appid=e1a1a30eca176ae42c8e3e3c6f3bb8ff
    String baseUrl="https://api.openweathermap.org/data/2.5/weather?q=";
    String API="&appid=e1a1a30eca176ae42c8e3e3c6f3bb8ff";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userCity=findViewById(R.id.getCity);
        resultUser=findViewById(R.id.result);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    final String myUrl = baseUrl + userCity.getText().toString() + API;


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myUrl, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i("JSON", "JSON: " + response);
                            try {

                                String info = response.getString("weather");
                                Log.i("INFO", "INFO: " + info);

                                JSONArray jsonArray = new JSONArray(info);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject parObj = jsonArray.getJSONObject(i);

                                    String myWeather = parObj.getString("main");
                                    resultUser.setText(myWeather);
                                    Log.i("ID", "ID: " + parObj.getString("id"));
                                    Log.i("MAIN", "MAIN: " + parObj.getString("main"));
                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("Error", "Something Went Wrong", error);
                        }
                    });

                    MYSingleton.getInstance(MainActivity.this).addTORequestQueue(jsonObjectRequest);
                }
            });

    }
}
