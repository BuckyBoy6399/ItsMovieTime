package com.example.rajraval.itsmovietime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Main extends Fragment {

    RecyclerView rv;
    RequestQueue requestQueue;
    List<MovieModel> mm;
    MovieAdapter adapter;
    Realm realm;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        rv = view.findViewById(R.id.rcv);
        mm = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());
        final StringRequest sr = new StringRequest(Request.Method.POST, "http://letsweb.in/demo/bustrack/api/getmovie.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Movie Response",response);
                try{
                    JSONObject res = new JSONObject(response);
                    String status = res.getString("status");
                    String message =  res.getString("message");
                    if(status.equalsIgnoreCase("success")){
                        JSONArray data = res.getJSONArray("data");
                        for(int i=0;i<data.length();i++)
                        {
                            JSONObject mmd = data.getJSONObject(i);
                            MovieModel moviedata = new MovieModel();
                            moviedata.setName(mmd.getString("movie"));
                            moviedata.setImage(mmd.getString("link"));
                            mm.add(moviedata);
                        }
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(mm);
                        realm.commitTransaction();
                        rv.setLayoutManager(new GridLayoutManager(getContext(),2));
                        adapter = new MovieAdapter(getContext(),mm);
                        rv.setAdapter(adapter);
                    }
                    else {
                        Toast.makeText(getContext(),"INTERNET CONNECTIVITY ERROR",Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("login error response", error.toString());
            }
        });
        requestQueue.add(sr);

       return view;
    }
}
