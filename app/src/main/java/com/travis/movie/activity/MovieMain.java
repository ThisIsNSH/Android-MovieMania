package com.travis.movie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ramotion.directselect.DSListView;
import com.travis.movie.adapter.AdvancedExampleCountryAdapter;
import com.travis.movie.model.AdvancedExampleCountryPOJO;
import com.travis.movie.R;
import com.travis.movie.adapter.MovieAdapter;
import com.travis.movie.extra.download1;
import com.travis.movie.extra.video;
import com.travis.movie.model.Movie;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieMain extends AppCompatActivity {

    RecyclerView movie;
    List<Movie> movieList;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_movie_main);
        initUI();
    }

    public void initUI() {
        movie = findViewById(R.id.movie);
        setupData();

    }

    public void setupData() {

        List<AdvancedExampleCountryPOJO> exampleDataSet = AdvancedExampleCountryPOJO.getExampleDataset();

        // Create adapter with our dataset
        ArrayAdapter<AdvancedExampleCountryPOJO> adapter = new AdvancedExampleCountryAdapter(
                this, R.layout.advanced_example_country_list_item, exampleDataSet);

        // Set adapter to our DSListView
        DSListView<AdvancedExampleCountryPOJO> pickerView = findViewById(R.id.ds_county_list);
        pickerView.setAdapter(adapter);

        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(MovieMain.this, movieList);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        movie.setLayoutManager(llm);
        movie.setAdapter(movieAdapter);
        movie.setItemAnimator(new DefaultItemAnimator());
        movie.setFocusable(false);
        getData();
    }

    public void getData() {
        Movie movie = new Movie("Jurassic World: Fallen Kingdom", "https://image.tmdb.org/t/p/original/c9XxwwhPHdaImA2f1WEfEsbhaFB.jpg", "2018");
        movieList.add(movie);
        movieList.add(movie);
        movieList.add(movie);
        movieList.add(movie);
        movieAdapter.notifyDataSetChanged();
    }

    public static class MainActivity extends Activity {
        String url, name, duration;
        TextView title1, durationd;
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
                    view.getContext().startActivity(intent);
                }
            });
            Button play = (Button) findViewById(R.id.playonline);
            play.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), video.class);
                    view.getContext().startActivity(intent);
                }
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
                public void onErrorResponse(VolleyError error) {
                }
            });

            rq.add(jsonObjectRequest);
        }

    }
}
