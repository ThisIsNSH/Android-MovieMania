package com.travis.movie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.travis.movie.R;
import com.travis.movie.adapter.AdvancedExampleCountryAdapter;
import com.travis.movie.adapter.MovieAdapter;
import com.travis.movie.extra.AdvancedExampleCountryPickerBox;
import com.travis.movie.extra.download1;
import com.travis.movie.extra.video;
import com.travis.movie.model.AdvancedExampleCountryPOJO;
import com.travis.movie.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieMain extends AppCompatActivity {

    LinearLayoutManager linearLayoutManager2018, linearLayoutManager2017, linearLayoutManager2016, linearLayoutManager2015, linearLayoutManager2014, linearLayoutManager2013, linearLayoutManager2012;

    AdvancedExampleCountryPickerBox ds_country_picker;
    DSListView ds_county_list;

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
        ds_country_picker = findViewById(R.id.ds_country_picker);
        movie2017 = findViewById(R.id.movie2017);
        movie2018 = findViewById(R.id.movie2018);
        movie2016 = findViewById(R.id.movie2016);
        movie2015 = findViewById(R.id.movie2015);
        movie2014 = findViewById(R.id.movie2014);
        movie2012 = findViewById(R.id.movie2012);
        movie2013 = findViewById(R.id.movie2013);
        ds_county_list = findViewById(R.id.ds_county_list);
        setupData();

    }

    public void setupData() {

        List<AdvancedExampleCountryPOJO> exampleDataSet = AdvancedExampleCountryPOJO.getExampleDataset();
        final ArrayAdapter<AdvancedExampleCountryPOJO> adapter = new AdvancedExampleCountryAdapter(
                this, R.layout.advanced_example_country_list_item, exampleDataSet);
        DSListView<AdvancedExampleCountryPOJO> pickerView = findViewById(R.id.ds_county_list);
        pickerView.setAdapter(adapter);

        movieList = new ArrayList<>();
        movieList2018 = new ArrayList<>();
        movieList2017 = new ArrayList<>();
        movieList2016 = new ArrayList<>();
        movieList2015 = new ArrayList<>();
        movieList2014 = new ArrayList<>();
        movieList2013 = new ArrayList<>();
        movieList2012 = new ArrayList<>();

        movieAdapter2018 = new MovieAdapter(MovieMain.this, movieList2018);
        movieAdapter2017 = new MovieAdapter(MovieMain.this, movieList2017);
        movieAdapter2016 = new MovieAdapter(MovieMain.this, movieList2016);
        movieAdapter2015 = new MovieAdapter(MovieMain.this, movieList2015);
        movieAdapter2014 = new MovieAdapter(MovieMain.this, movieList2014);
        movieAdapter2013 = new MovieAdapter(MovieMain.this, movieList2013);
        movieAdapter2012 = new MovieAdapter(MovieMain.this, movieList2012);

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

        movie2018.setAdapter(movieAdapter2018);
        movie2017.setAdapter(movieAdapter2017);
        movie2016.setAdapter(movieAdapter2016);
        movie2015.setAdapter(movieAdapter2015);
        movie2014.setAdapter(movieAdapter2014);
        movie2013.setAdapter(movieAdapter2013);
        movie2012.setAdapter(movieAdapter2012);

        ds_country_picker.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                AdvancedExampleCountryPickerBox a = (AdvancedExampleCountryPickerBox) v;
                int a1;

                System.out.println(a.text.getText());
                if (a.text.getText().toString().equals(""))
                    a1 = 0;
                else
                    a1 = Integer.parseInt(a.text.getText().toString());

                switch (a1) {
                    case 2018:
                        getData(2018);
                        movieAdapter2018.notifyDataSetChanged();
                        movie2018.setVisibility(View.VISIBLE);
                        movie2017.setVisibility(View.GONE);
                        movie2016.setVisibility(View.GONE);
                        movie2015.setVisibility(View.GONE);
                        movie2014.setVisibility(View.GONE);
                        movie2013.setVisibility(View.GONE);
                        movie2012.setVisibility(View.GONE);
                        movieAdapter2018.notifyDataSetChanged();
                        break;
                    case 2017:
                        getData(2017);
                        movieAdapter2017.notifyDataSetChanged();
                        movie2018.setVisibility(View.GONE);
                        movie2017.setVisibility(View.VISIBLE);
                        movie2016.setVisibility(View.GONE);
                        movie2015.setVisibility(View.GONE);
                        movie2014.setVisibility(View.GONE);
                        movie2013.setVisibility(View.GONE);
                        movie2012.setVisibility(View.GONE);
                        movieAdapter2017.notifyDataSetChanged();
                        break;
                    case 2016:
                        getData(2016);
                        movieAdapter2016.notifyDataSetChanged();
                        movie2018.setVisibility(View.GONE);
                        movie2017.setVisibility(View.GONE);
                        movie2016.setVisibility(View.VISIBLE);
                        movie2015.setVisibility(View.GONE);
                        movie2014.setVisibility(View.GONE);
                        movie2013.setVisibility(View.GONE);
                        movie2012.setVisibility(View.GONE);
                        movieAdapter2016.notifyDataSetChanged();
                        break;
                    case 2015:
                        getData(2015);
                        movieAdapter2015.notifyDataSetChanged();
                        movie2018.setVisibility(View.GONE);
                        movie2017.setVisibility(View.GONE);
                        movie2016.setVisibility(View.GONE);
                        movie2015.setVisibility(View.VISIBLE);
                        movie2014.setVisibility(View.GONE);
                        movie2013.setVisibility(View.GONE);
                        movie2012.setVisibility(View.GONE);
                        movieAdapter2015.notifyDataSetChanged();
                        break;
                    case 2014:
                        getData(2014);
                        movieAdapter2014.notifyDataSetChanged();
                        movie2018.setVisibility(View.GONE);
                        movie2017.setVisibility(View.GONE);
                        movie2016.setVisibility(View.GONE);
                        movie2015.setVisibility(View.GONE);
                        movie2014.setVisibility(View.VISIBLE);
                        movie2013.setVisibility(View.GONE);
                        movie2012.setVisibility(View.GONE);
                        movieAdapter2014.notifyDataSetChanged();
                        break;
                    case 2013:
                        getData(2013);
                        movieAdapter2013.notifyDataSetChanged();
                        movie2018.setVisibility(View.GONE);
                        movie2017.setVisibility(View.GONE);
                        movie2016.setVisibility(View.GONE);
                        movie2015.setVisibility(View.GONE);
                        movie2014.setVisibility(View.GONE);
                        movie2013.setVisibility(View.VISIBLE);
                        movie2012.setVisibility(View.GONE);
                        movieAdapter2013.notifyDataSetChanged();
                        break;
                    case 2012:
                        getData(2012);
                        movieAdapter2012.notifyDataSetChanged();
                        movie2018.setVisibility(View.GONE);
                        movie2017.setVisibility(View.GONE);
                        movie2016.setVisibility(View.GONE);
                        movie2015.setVisibility(View.GONE);
                        movie2014.setVisibility(View.GONE);
                        movie2013.setVisibility(View.GONE);
                        movie2012.setVisibility(View.VISIBLE);
                        movieAdapter2012.notifyDataSetChanged();
                        break;
                    default:
                        getData(2018);
                        movieAdapter2018.notifyDataSetChanged();
                        movie2018.setVisibility(View.VISIBLE);
                        movie2017.setVisibility(View.GONE);
                        movie2016.setVisibility(View.GONE);
                        movie2015.setVisibility(View.GONE);
                        movie2014.setVisibility(View.GONE);
                        movie2013.setVisibility(View.GONE);
                        movie2012.setVisibility(View.GONE);
                        movieAdapter2018.notifyDataSetChanged();
                        break;
                }
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
                            "},\n" +
                            "{\"id\": 348350,\"title\":\"Solo: A Star Wars Story\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 455980,\"title\":\"Tag\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 454983,\"title\":\"The Kissing Booth\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 338970,\"title\":\"Tomb Raider\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 284054,\"title\":\"Black Panther\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 406761,\"title\":\"Hotel Artemis\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 500475,\"title\":\"SuperFly\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 500664,\"title\":\"Upgrade\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 445571,\"title\":\"Game Night\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 447332,\"title\":\"A Quiet Place\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 401981,\"title\":\"Red Sparrow\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 395990,\"title\":\"Death Wish\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 449176,\"title\":\"Love, Simon\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 300668,\"title\":\"Annihilation\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 429300,\"title\":\"Adrift\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 339103,\"title\":\"Gotti\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 363088,\"title\":\"Ant-Man and the Wasp\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 511785,\"title\":\"Alex Strangelove\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 333339,\"title\":\"Ready Player One\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 407451,\"title\":\"A Wrinkle in Time\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 502682,\"title\":\"Book Club\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 440471,\"title\":\"Escape Plan 2: Hades\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 449443,\"title\":\"Den of Thieves\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 370567,\"title\":\"Sherlock Gnomes\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 454619,\"title\":\"Overboard\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 384677,\"title\":\"Set It Up\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 476299,\"title\":\"Ghostland\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 467660,\"title\":\"Unsane\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 371608,\"title\":\"The Strangers: Prey at Night\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 400535,\"title\":\"Sicario: Day of the Soldado\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "}\n" +
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
                            "{\"id\":337170,\"title\":\"American Made\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":353486,\"title\":\"Jumanji Welcome to the Jungle\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":141052,\"title\":\"Justice League\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":339846,\"title\":\"Baywatch\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":315635,\"title\":\"Spider-Man: Homecoming\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":181808,\"title\":\"Star Wars: The Last Jedi\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":391713,\"title\":\"Lady Bird\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":297762,\"title\":\"Wonder Woman\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":399055,\"title\":\"The Shape of Water\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":283995,\"title\":\"Guardians of the Galaxy Vol. 2\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":371638,\"title\":\"The Disaster Artist\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":359940,\"title\":\"Three Billboards Outside Ebbing, Missouri\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":381283,\"title\":\"Mother!\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":425972,\"title\":\"Cargo\",\"v_url\":\"null\"}\n" +
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
                            "{\"id\":292431,\"title\":\"Love\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":286217,\"title\":\"The Martian\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":216015,\"title\":\"Fifty Shades of Grey\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":131634,\"title\":\"The Hunger Games: Mockingjay - Part 2\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":150540,\"title\":\"Inside Out\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":273248,\"title\":\"The Hateful Eight\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":140607,\"title\":\"Star Wars: The Force Awakens\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":206647,\"title\":\"Spectre\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":150689,\"title\":\"Cinderella\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":177677,\"title\":\"Mission: Impossible - Rogue Nation\",\"v_url\":\"null\"}\n" +
                            "]}}\n");

                    jsonArray = jsonObject.getJSONObject("movie").getJSONArray("2015");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        movieObject = new Movie(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("v_url"), jsonArray.getJSONObject(i).getString("id"));
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
                            "{\"id\":292431,\"title\":\"Love\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":286217,\"title\":\"The Martian\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":216015,\"title\":\"Fifty Shades of Grey\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":131634,\"title\":\"The Hunger Games: Mockingjay - Part 2\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":150540,\"title\":\"Inside Out\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":273248,\"title\":\"The Hateful Eight\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":140607,\"title\":\"Star Wars: The Force Awakens\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":206647,\"title\":\"Spectre\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":150689,\"title\":\"Cinderella\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":177677,\"title\":\"Mission: Impossible - Rogue Nation\",\"v_url\":\"null\"}\n" +
                            "]}}\n");
                    jsonArray = jsonObject.getJSONObject("movie").getJSONArray("2015");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        movieObject = new Movie(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("v_url"), jsonArray.getJSONObject(i).getString("id"));
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
                            "},\n" +
                            "{\"id\": 136797,\"title\":\"Need for Speed\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 145220,\"title\":\"Muppets Most Wanted\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 100402,\"title\":\"Captain America: The Winter Soldier\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 172385,\"title\":\"Rio 2\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 102382,\"title\":\"The Amazing Spider-Man 2\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 195589,\"title\":\"Neighbors\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 124905,\"title\":\"Godzilla\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 198185,\"title\":\"Million Dollar Arm\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 127585,\"title\":\"X-Men: Days of Future Past\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 188161,\"title\":\"A Million Ways to Die in the West\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 82702,\"title\":\"How to Train Your Dragon 2\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 119450,\"title\":\"Dawn of the Planet of the Apes\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 184315,\"title\":\"Hercules\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 118340,\"title\":\"Guardians of the Galaxy\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 138103,\"title\":\"The Expendables 3\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "{\"id\": 156022,\"title\":\"The Equalizer\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "}\n" +
                            "]}}");
                    jsonArray = jsonObject.getJSONObject("movie").getJSONArray("2014");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        movieObject = new Movie(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("v_url"), jsonArray.getJSONObject(i).getString("id"));
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
                            "{\"id\":107846,\"title\":\"Escape Plan\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":146233,\"title\":\"Prisoners\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":68721,\"title\":\"Iron Man 3\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":49521,\"title\":\"Man of Steel\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":152584,\"title\":\"Blue Is the Warmest Color\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":158015,\"title\":\"The Purge\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":160588,\"title\":\"Blue Jasmine\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":101299,\"title\":\"The Hunger Games: Catching Fire\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":258216,\"title\":\"Nymphomaniac: Vol. I\",\"v_url\":\"null\"},\n" +
                            "\n" +
                            "{\"id\":64682,\"title\":\"The Great Gatsby\",\"v_url\":\"null\"}\n" +
                            "]}}");
                    jsonArray = jsonObject.getJSONObject("movie").getJSONArray("2013");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        movieObject = new Movie(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("v_url"), jsonArray.getJSONObject(i).getString("id"));
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
                            "},\n" +
                            "\n" +
                            "{\"id\": 44833,\"title\":\"Battleship\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 114150,\"title\":\"Pitch Perfect\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 80278,\"title\":\"The Impossible\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 84892,\"title\":\"The Perks of Being a Wallflower\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 83542,\"title\":\"Cloud Atlas\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 1930,\"title\":\"The Amazing Spider-Man\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 49529,\"title\":\"John Carter\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 37724,\"title\":\"Skyfall\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 82525,\"title\":\"Savages\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 72976,\"title\":\"Lincoln\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 84199,\"title\":\"The First Time\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 62213,\"title\":\"Dark Shadows\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 82507,\"title\":\"Sinister\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 72545,\"title\":\"Journey 2: The Mysterious Island\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 76493,\"title\":\"The Dictator\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "},\n" +
                            "\n" +
                            "{\"id\": 87827,\"title\":\"Life of Pi\",\"v_url\":\"null\"\n" +
                            "\n" +
                            "}\n" +
                            "\n" +
                            "]}}");
                    jsonArray = jsonObject.getJSONObject("movie").getJSONArray("2012");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        movieObject = new Movie(jsonArray.getJSONObject(i).getString("title"), jsonArray.getJSONObject(i).getString("v_url"), jsonArray.getJSONObject(i).getString("id"));
                        movieList2012.add(movieObject);
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
