package com.travis.movie.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.travis.movie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.travis.movie.activity.GlideOptions.bitmapTransform;

/**
 * Created by LENOVO on 02-07-2018.
 */

public class AboutMovie extends Activity implements DialogInterface.OnClickListener {
    String overview,production_companies="";
    TextView movie_production,movie_overview;
    RequestQueue details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_dialog_box);
        production_companies += "PRODUCTION_COMPANIES\n";
        details = Volley.newRequestQueue(this);
        JsonObjectRequest jsObj = new JsonObjectRequest
                (Request.Method.GET, "http://api.themoviedb.org/3/movie/" + "550" + "?api_key=c94d74f77ae9409c43d2d3d74a1c5d3f&append_to_response=videos", null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            overview  = response.getString("overview");
                            overview += "\n";
                            JSONArray productionCompanies = response.getJSONArray("production_companies");
                            int total = productionCompanies.length();
                            for(int i = 0;i<total;i++)
                            {
                                JSONObject production = productionCompanies.getJSONObject(i);
                                production_companies += (production.getString("name")+" "+production.getString("origin_country")+"\n");
                            }
                            movie_overview = findViewById(R.id.movie_overview);
                            movie_overview.setText("OVERVIEW\n"+overview);
                            movie_production = findViewById(R.id.movie_production);
                            movie_production.setText(production_companies);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                    }
                });

        details.add(jsObj);

    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
