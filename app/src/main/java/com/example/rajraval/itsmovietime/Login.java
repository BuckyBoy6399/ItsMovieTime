package com.example.rajraval.itsmovietime;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity{
    TextInputEditText emailidone,userpassword;
    TextView regit;
    Button buttonOne;
    String s1,s2;
    String a = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        requestQueue = Volley.newRequestQueue(this);
        emailidone = findViewById(R.id.input_email);
        userpassword = findViewById(R.id.input_password);
        buttonOne = findViewById(R.id.buttont);
        regit = findViewById(R.id.text);
        buttonOne.setOnClickListener((view)->{
            s1=String.valueOf(emailidone.getText());
            s2=String.valueOf(userpassword.getText());
            if (s1.equals("") || s1 == null) {
                Toast.makeText(Login.this, "Please enter your Email", Toast.LENGTH_LONG).show();
            } else if (s2.equals("") || s2 == null) {
                Toast.makeText(Login.this, "Please enter your Password", Toast.LENGTH_LONG).show();
            } else if (!s1.matches(a)) {
                Toast.makeText(Login.this, "Please enter Valid Email", Toast.LENGTH_LONG).show();
            } else if (s2.length() < 6) {
                Toast.makeText(Login.this, "Please enter Password more than Six Characters", Toast.LENGTH_LONG).show();
            } else{
                Intent i = new Intent(Login.this, homescreen.class);
                startActivity(i);
                StringRequest sr = new StringRequest(Request.Method.POST, "http://letsweb.in/demo/bustrack/api/login.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("login response", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");
                            if (status.equals("Success")) {
                                Intent i = new Intent(Login.this, homescreen.class);
                                startActivity(i);
                                i.putExtra("email", s1);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse (VolleyError error){
                        Log.d("login error response", error.toString());
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", s1);
                        params.put("password", s2);
                        return params;
                    }
                };
                requestQueue.add(sr);
            }
        });
    }
}
