package com.travis.movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
    String url, name,duration;
    TextView title1,durationd;
    RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title1 = (TextView) findViewById(R.id.title);
        durationd = (TextView) findViewById(R.id.dura);
        rq = Volley.newRequestQueue(this);
        Button download = (Button) findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), download1.class);
                view.getContext().startActivity(intent);}
        });
         Button play = (Button) findViewById(R.id.playonline);
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), video.class);
                view.getContext().startActivity(intent);}
        });
        JSON();

    }
    public void JSON() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/3085?api_key=c94d74f77ae9409c43d2d3d74a1c5d3f", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    name = response.getString("original_title");
                    duration = response.getString("runtime");
                    title1.setText(name);
durationd.setText(duration + "minutes");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
            }
        });

       rq.add(jsonObjectRequest);
    }

    }