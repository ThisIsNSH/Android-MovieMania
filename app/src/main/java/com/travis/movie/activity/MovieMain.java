package com.travis.movie.activity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.travis.movie.R;
import com.travis.movie.adapter.MovieAdapter;
import com.travis.movie.extra.download1;
import com.travis.movie.extra.video;
import com.travis.movie.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class MovieMain extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager2018, linearLayoutManager2017, linearLayoutManager2016, linearLayoutManager2015, linearLayoutManager2014, linearLayoutManager2013, linearLayoutManager2012;
    Movie movieObject;
    JSONArray jsonArray;
    JSONObject jsonObject;
    MovieAdapter movieAdapter, movieAdapter2018, movieAdapter2017, movieAdapter2016, movieAdapter2015, movieAdapter2014, movieAdapter2013, movieAdapter2012;
    List<Movie> movieList, movieList2018, movieList2017, movieList2016, movieList2015, movieList2014, movieList2013, movieList2012;
    RecyclerView movie, movie2018, movie2017, movie2016, movie2015, movie2014, movie2013, movie2012;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_movie_main);
        initUI();
    }
    public void initUI() {
        movie2017 = findViewById(R.id.movie2017);
        movie2018 = findViewById(R.id.movie2018);
        movie2016 = findViewById(R.id.movie2016);
        movie2015 = findViewById(R.id.movie2015);
        movie2014 = findViewById(R.id.movie2014);
        movie2012 = findViewById(R.id.movie2012);
        movie2013 = findViewById(R.id.movie2013);

        movie2012.setVisibility(View.VISIBLE);
        movie2013.setVisibility(View.VISIBLE);
        movie2014.setVisibility(View.VISIBLE);
        movie2015.setVisibility(View.VISIBLE);
        movie2016.setVisibility(View.VISIBLE);
        movie2017.setVisibility(View.VISIBLE);
        movie2018.setVisibility(View.VISIBLE);
//        ds_county_list = findViewById(R.id.ds_county_list);
        setupData();
    }
    @SuppressLint("ResourceAsColor")
    public void setupData() {

        movieList = new ArrayList<>();
        movieList2018 = new ArrayList<>();
        movieList2017 = new ArrayList<>();
        movieList2016 = new ArrayList<>();
        movieList2015 = new ArrayList<>();
        movieList2014 = new ArrayList<>();
        movieList2013 = new ArrayList<>();
        movieList2012 = new ArrayList<>();

        linearLayoutManager2018 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager2017 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager2016 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager2015 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager2014 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager2013 = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager2012 = new LinearLayoutManager(getApplicationContext());

        linearLayoutManager2018.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager2017.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager2016.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager2015.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager2014.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager2013.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager2012.setOrientation(LinearLayoutManager.HORIZONTAL);

        movie2018.setLayoutManager(linearLayoutManager2018);
        movie2017.setLayoutManager(linearLayoutManager2017);
        movie2016.setLayoutManager(linearLayoutManager2016);
        movie2015.setLayoutManager(linearLayoutManager2015);
        movie2014.setLayoutManager(linearLayoutManager2014);
        movie2013.setLayoutManager(linearLayoutManager2013);
        movie2012.setLayoutManager(linearLayoutManager2012);

        movieAdapter2018 = new MovieAdapter(MovieMain.this, movieList2018);
        movieAdapter2017 = new MovieAdapter(MovieMain.this, movieList2017);
        movieAdapter2016 = new MovieAdapter(MovieMain.this, movieList2016);
        movieAdapter2015 = new MovieAdapter(MovieMain.this, movieList2015);
        movieAdapter2014 = new MovieAdapter(MovieMain.this, movieList2014);
        movieAdapter2013 = new MovieAdapter(MovieMain.this, movieList2013);
        movieAdapter2012 = new MovieAdapter(MovieMain.this, movieList2012);

        movie2018.setAdapter(movieAdapter2018);
        movie2017.setAdapter(movieAdapter2017);
        movie2016.setAdapter(movieAdapter2016);
        movie2015.setAdapter(movieAdapter2015);
        movie2014.setAdapter(movieAdapter2014);
        movie2013.setAdapter(movieAdapter2013);
        movie2012.setAdapter(movieAdapter2012);

        getData(2018);
        movieAdapter2018.notifyDataSetChanged();
        getData(2017);
        movieAdapter2017.notifyDataSetChanged();
        getData(2015);
        movieAdapter2015.notifyDataSetChanged();
        getData(2016);
        movieAdapter2016.notifyDataSetChanged();
        getData(2014);
        movieAdapter2014.notifyDataSetChanged();
        getData(2013);
        movieAdapter2013.notifyDataSetChanged();
        getData(2012);
        movieAdapter2012.notifyDataSetChanged();
        final TextView m18 = findViewById(R.id.m2018);
        final TextView m17 = findViewById(R.id.m2017);
        final TextView m16 = findViewById(R.id.m2016);
        final TextView m15 = findViewById(R.id.m2015);
        final TextView m14 = findViewById(R.id.m2014);
        final TextView m13 = findViewById(R.id.m2013);
        final TextView m12 = findViewById(R.id.m2012);

        movie2018.setVisibility(View.VISIBLE);
        movie2017.setVisibility(View.GONE);
        movie2016.setVisibility(View.GONE);
        movie2015.setVisibility(View.GONE);
        movie2014.setVisibility(View.GONE);
        movie2013.setVisibility(View.GONE);
        movie2012.setVisibility(View.GONE);
        m18.setTextColor(R.color.black);
        m17.setTextColor(R.color.year);
        m16.setTextColor(R.color.year);
        m15.setTextColor(R.color.year);
        m14.setTextColor(R.color.year);
        m13.setTextColor(R.color.year);
        m12.setTextColor(R.color.year);
        m18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie2018.setVisibility(View.VISIBLE);
                movie2017.setVisibility(View.GONE);
                movie2016.setVisibility(View.GONE);
                movie2015.setVisibility(View.GONE);
                movie2014.setVisibility(View.GONE);
                movie2013.setVisibility(View.GONE);
                movie2012.setVisibility(View.GONE);
                m18.setTextColor(R.color.black);
                m17.setTextColor(R.color.year);
                m16.setTextColor(R.color.year);
                m15.setTextColor(R.color.year);
                m14.setTextColor(R.color.year);
                m13.setTextColor(R.color.year);
                m12.setTextColor(R.color.year);
            }
        });
        m17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie2018.setVisibility(View.GONE);
                movie2017.setVisibility(View.VISIBLE);
                movie2016.setVisibility(View.GONE);
                movie2015.setVisibility(View.GONE);
                movie2014.setVisibility(View.GONE);
                movie2013.setVisibility(View.GONE);
                movie2012.setVisibility(View.GONE);
                m18.setTextColor(R.color.year);
                m17.setTextColor(R.color.black);
                m16.setTextColor(R.color.year);
                m15.setTextColor(R.color.year);
                m14.setTextColor(R.color.year);
                m13.setTextColor(R.color.year);
                m12.setTextColor(R.color.year);
            }
        });

        m16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movie2018.setVisibility(View.GONE);
                movie2017.setVisibility(View.GONE);
                movie2016.setVisibility(View.VISIBLE);
                movie2015.setVisibility(View.GONE);
                movie2014.setVisibility(View.GONE);
                movie2013.setVisibility(View.GONE);
                movie2012.setVisibility(View.GONE);
                m18.setTextColor(R.color.year);
                m17.setTextColor(R.color.year);
                m16.setTextColor(R.color.black);
                m15.setTextColor(R.color.year);
                m14.setTextColor(R.color.year);
                m13.setTextColor(R.color.year);
                m12.setTextColor(R.color.year);
            }
        });

        m15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movie2018.setVisibility(View.GONE);
                movie2017.setVisibility(View.GONE);
                movie2016.setVisibility(View.GONE);
                movie2015.setVisibility(View.VISIBLE);
                movie2014.setVisibility(View.GONE);
                movie2013.setVisibility(View.GONE);
                movie2012.setVisibility(View.GONE);
                m18.setTextColor(R.color.year);
                m17.setTextColor(R.color.year);
                m16.setTextColor(R.color.year);
                m15.setTextColor(R.color.black);
                m14.setTextColor(R.color.year);
                m13.setTextColor(R.color.year);
                m12.setTextColor(R.color.year);
            }
        });


        m14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movie2018.setVisibility(View.GONE);
                movie2017.setVisibility(View.GONE);
                movie2016.setVisibility(View.GONE);
                movie2015.setVisibility(View.GONE);
                movie2014.setVisibility(View.VISIBLE);
                movie2013.setVisibility(View.GONE);
                movie2012.setVisibility(View.GONE);
                m18.setTextColor(R.color.year);
                m17.setTextColor(R.color.year);
                m16.setTextColor(R.color.year);
                m15.setTextColor(R.color.year);
                m14.setTextColor(R.color.black);
                m13.setTextColor(R.color.year);
                m12.setTextColor(R.color.year);
            }
        });

        m13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movie2018.setVisibility(View.GONE);
                movie2017.setVisibility(View.GONE);
                movie2016.setVisibility(View.GONE);
                movie2015.setVisibility(View.GONE);
                movie2014.setVisibility(View.GONE);
                movie2013.setVisibility(View.VISIBLE);
                movie2012.setVisibility(View.GONE);
                m18.setTextColor(R.color.year);
                m17.setTextColor(R.color.year);
                m16.setTextColor(R.color.year);
                m15.setTextColor(R.color.year);
                m14.setTextColor(R.color.year);
                m13.setTextColor(R.color.black);
                m12.setTextColor(R.color.year);
            }
        });

        m12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movie2018.setVisibility(View.GONE);
                movie2017.setVisibility(View.GONE);
                movie2016.setVisibility(View.GONE);
                movie2015.setVisibility(View.GONE);
                movie2014.setVisibility(View.GONE);
                movie2013.setVisibility(View.GONE);
                movie2012.setVisibility(View.VISIBLE);
                m18.setTextColor(R.color.year);
                m17.setTextColor(R.color.year);
                m16.setTextColor(R.color.year);
                m15.setTextColor(R.color.year);
                m14.setTextColor(R.color.year);
                m13.setTextColor(R.color.year);
                m12.setTextColor(R.color.black);
            }
        });

    }

    public void getData(int a) {
        try {
            switch (a) {
                case 2018:
                    jsonObject = new JSONObject("{\n" +
                            "\"movie\":{\n" +
                            "\"2018\":[\n" +
                            "\n" +
                            "{\"id\": 260513,\"title\":\"Incredibles 2\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 351286,\"title\":\"Jurassic World: Fallen Kingdom\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\":402900 ,\"title\":\"Ocean's 8\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\":493922 ,\"title\":\"Hereditary\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 299536,\"title\":\"Avengers: Infinity War\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 383498,\"title\":\"Deadpool 2\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "}" +
                            "]}}");
                    jsonArray = jsonObject.getJSONObject("movie").getJSONArray("2018");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        movieObject = new Movie(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("v_url"), jsonArray.getJSONObject(i).getString("id"));
                        System.out.println(jsonArray.getJSONObject(i).getString("title"));
                        movieList2018.add(movieObject);
                        movieAdapter2018.notifyDataSetChanged();
                    }

                    break;
                case 2017:
                    jsonObject = new JSONObject("{\n" +
                            "\"movie\":{\n" +
                            "\"2017\":[\n" +
                            "{\"id\":284053,\"title\":\"Thor Ragnarok\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":316029,\"title\":\"The Greatest Showman\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":354912,\"title\":\"Coco\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":335984,\"title\":\"Blade Runner 2049\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":346364,\"title\":\"It\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":337170,\"title\":\"American Made\",\"v_url\":\"null\"}" +
                            "]}}\n");
                    jsonArray = jsonObject.getJSONObject("movie").getJSONArray("2017");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        movieObject = new Movie(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("v_url"), jsonArray.getJSONObject(i).getString("id"));
                        System.out.println(jsonArray.getJSONObject(i).getString("title"));
                        movieList2017.add(movieObject);
                        movieAdapter2017.notifyDataSetChanged();
                    }
                    break;

                case 2016:
                    jsonObject = new JSONObject("{\n" +
                            "\"movie\":{\n" +
                            "\"2015\":[\n" +
                            "{\"id\":135397,\"title\":\"Jurassic World\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":99861,\"title\":\"Avengers: Age of Ultron\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":273481,\"title\":\"Sicario\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":76341,\"title\":\"Mad Max: Fury Road\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":102899,\"title\":\"Ant-Man\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":292431,\"title\":\"Love\",\"v_url\":\"null\"}\n" +
                            "]}}\n");

                    jsonArray = jsonObject.getJSONObject("movie").getJSONArray("2015");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        movieObject = new Movie(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("v_url"), jsonArray.getJSONObject(i).getString("id"));
                        System.out.println(jsonArray.getJSONObject(i).getString("title"));
                        movieList2016.add(movieObject);
                        movieAdapter2016.notifyDataSetChanged();
                    }
                    break;
                case 2015:
                    jsonObject = new JSONObject("{\n" +
                            "\"movie\":{\n" +
                            "\"2015\":[\n" +
                            "{\"id\":135397,\"title\":\"Jurassic World\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":99861,\"title\":\"Avengers: Age of Ultron\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":273481,\"title\":\"Sicario\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":76341,\"title\":\"Mad Max: Fury Road\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":102899,\"title\":\"Ant-Man\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":292431,\"title\":\"Love\",\"v_url\":\"null\"}\n" +
                            "]}}\n");
                    jsonArray = jsonObject.getJSONObject("movie").getJSONArray("2015");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        movieObject = new Movie(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("v_url"), jsonArray.getJSONObject(i).getString("id"));
                        System.out.println(jsonArray.getJSONObject(i).getString("title"));
                        movieList2015.add(movieObject);
                        movieAdapter2015.notifyDataSetChanged();
                    }
                    break;
                case 2014:
                    jsonObject = new JSONObject("{\n" +
                            "\"movie\":{\n" +
                            "\"2014\":[\n" +
                            "{\"id\": 85350,\"title\":\"Boyhood\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 225574,\"title\":\"Non-Stop\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 137106,\"title\":\"The Lego Movie\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 53182,\"title\":\"300: Rise of an Empire\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 82703,\"title\":\"Mr. Peabody &amp; Sherman\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 120467,\"title\":\"The Grand Budapest Hotel\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "}\n" +
                            "]}}");
                    jsonArray = jsonObject.getJSONObject("movie").getJSONArray("2014");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        movieObject = new Movie(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("v_url"), jsonArray.getJSONObject(i).getString("id"));
                        System.out.println(jsonArray.getJSONObject(i).getString("title"));
                        movieList2014.add(movieObject);
                        movieAdapter2014.notifyDataSetChanged();
                    }
                    break;
                case 2013:
                    jsonObject = new JSONObject("{\n" +
                            "\"movie\":{\n" +
                            "\"2013\":[\n" +
                            "{\"id\":76338,\"title\":\"Thor: The Dark World\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":106646,\"title\":\"The Wolf of Wall Street\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":138843,\"title\":\"The Conjuring\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":109445,\"title\":\"Frozen\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":68726,\"title\":\"Pacific Rim\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":107846,\"title\":\"Escape Plan\",\"v_url\":\"null\"}\n" +
                            "\n" +
                            "]}}");
                    jsonArray = jsonObject.getJSONObject("movie").getJSONArray("2013");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        movieObject = new Movie(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("v_url"), jsonArray.getJSONObject(i).getString("id"));
                        System.out.println(jsonArray.getJSONObject(i).getString("title"));
                        movieList2013.add(movieObject);
                        movieAdapter2013.notifyDataSetChanged();
                    }
                    break;
                case 2012:
                    jsonObject = new JSONObject("{\n" +
                            "\"movie\":{\n" +
                            "\"2012\":[\n" +
                            "\n" +
                            "{\"id\": 24428,\"title\":\"The Avengers\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 70160,\"title\":\"The Hunger Games\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 22970,\"title\":\"The Cabin in the Woods\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 49026,\"title\":\"The Dark Knight Rises\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 49051,\"title\":\"The Hobbit: An Unexpected Journey\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 82690,\"title\":\"Wreck-It Ralph\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "}\n" +
                            "]}}");
                    jsonArray = jsonObject.getJSONObject("movie").getJSONArray("2012");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        movieObject = new Movie(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("v_url"), jsonArray.getJSONObject(i).getString("id"));
                        movieList2012.add(movieObject);
                        System.out.println(jsonArray.getJSONObject(i).getString("title"));
                        movieAdapter2012.notifyDataSetChanged();
                    }
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/3085?api_key="+getString(R.string.api_key), null, new Response.Listener<JSONObject>() {
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
