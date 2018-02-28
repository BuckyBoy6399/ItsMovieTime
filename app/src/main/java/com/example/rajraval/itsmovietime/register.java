package com.example.rajraval.itsmovietime;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    TextInputEditText name;
    TextInputEditText mobile;
    TextInputEditText email;
    TextInputEditText password;
    TextInputEditText confirmpassword;
    Button registerone;
    String s1, s2, s3, s4, s5;
    RequestQueue requestQueuea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        requestQueuea = Volley.newRequestQueue(this);
        name = findViewById(R.id.input_name);
        mobile = findViewById(R.id.input_mobileno);
        email = findViewById(R.id.input_emailb);
        password = findViewById(R.id.input_passab);
        confirmpassword = findViewById(R.id.input_passworda);
        registerone = findViewById(R.id.butttontwo);
        registerone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1 = String.valueOf(email.getText());
                s2 = String.valueOf(password.getText());
                s3 = String.valueOf(confirmpassword.getText());
                s4 = String.valueOf(mobile.getText());
                s5 = String.valueOf(name.getText());
                String a = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (s1.equals("") || s1 == null) {
                    Toast.makeText(register.this, "Please enter your Email", Toast.LENGTH_LONG).show();
                } else if (s2.equals("") || s2 == null) {
                    Toast.makeText(register.this, "Please enter your Password", Toast.LENGTH_LONG).show();
                } else if (!s1.matches(a)) {
                    Toast.makeText(register.this, "Please enter Valid Email", Toast.LENGTH_LONG).show();
                } else if (s2.length() < 6) {
                    Toast.makeText(register.this, "Please enter Password more than Six Characters", Toast.LENGTH_LONG).show();
                } else if (!s2.equals(s3)) {
                    Toast.makeText(register.this, "Password Not Matched", Toast.LENGTH_LONG).show();
                } else if (s5.equals("") || s5 == null) {
                    Toast.makeText(register.this, "Please enter your Name", Toast.LENGTH_LONG).show();
                } else if (s4.length() < 10) {
                    Toast.makeText(register.this, "Please enter a 10-digit Mobile Number", Toast.LENGTH_LONG).show();
                } else if (s4.equals("") || s4 == null) {
                    Toast.makeText(register.this, "Please enter your Mobile Number", Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(register.this, homescreen.class);
                    startActivity(i);
                    StringRequest sr = new StringRequest(Request.Method.POST, "http://letsweb.in/demo/bustrack/api/register.php", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("login response", response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                String message = jsonObject.getString("message");
                                if (status.equals("Success")) {
                                    Intent i = new Intent(register.this, homescreen.class);
                                    startActivity(i);
                                    i.putExtra("email", s1);


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },new ErrorListener()
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
                            params.put("name", s5);
                            return params;
                        }
                    };
                    requestQueuea.add(sr);
                }

                }

        });
    }
}


