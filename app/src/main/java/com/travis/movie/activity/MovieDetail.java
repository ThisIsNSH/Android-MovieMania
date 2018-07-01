package com.travis.movie.activity;

import android.animation.ObjectAnimator;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jgabrielfreitas.core.BlurImageView;
import com.squareup.picasso.Picasso;
import com.travis.movie.R;
import com.travis.movie.extra.OnSwipeTouchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetail extends AppCompatActivity {

   static  String id,poster,duration,release_date,moviename;
    RelativeLayout holder;
    static String videoid;
    CardView card;
    FrameLayout button;
    int check = 0;
    TextView title, runtime, release, revenue;
    ImageView mainImage,movieposter;
    BlurImageView backImage;
RequestQueue movielist;
    public static void watchYoutubeVideo(Context context, String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_movie_detail);
        initUI();
//        Bundle bundle = getIntent().getExtras();
//        id = bundle.getString("id","00000000");
        movieposter = (ImageView) findViewById(R.id.pic);
        runtime = (TextView) findViewById(R.id.runtime);
        release = (TextView) findViewById(R.id.release);
        movielist = Volley.newRequestQueue(this);
title = (TextView) findViewById(R.id.title);
backImage = (BlurImageView) findViewById(R.id.back);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, "http://api.themoviedb.org/3/movie/550?api_key=c94d74f77ae9409c43d2d3d74a1c5d3f&append_to_response=videos", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            duration = response.getString("runtime");
                            moviename = response.getString("title");
                            release_date = response.getString("release_date");
                            poster = response.getString("poster_path");
                            release.setText(release_date);
                            Picasso.get().load("http://image.tmdb.org/t/p/w500/"+poster).into(movieposter);
                            Picasso.get().load("http://image.tmdb.org/t/p/w500/"+poster).into(backImage);
                            runtime.setText(duration + " minutes");
                            title.setText(moviename);
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

        movielist.add(jsObjRequest);
        holder.setOnTouchListener(new OnSwipeTouchListener(MovieDetail.this) {

            public void onSwipeLeft() {
                if (check == 0) {
                    Animation animate = AnimationUtils.loadAnimation(MovieDetail.this, R.anim.translate_left);
                    card.startAnimation(animate);
                    ObjectAnimator animation = ObjectAnimator.ofFloat(card, "rotationY", 0.0f, 5f);
                    animation.setDuration(300);
                    animation.setInterpolator(new AccelerateDecelerateInterpolator());
                    animation.start();


                    Animation animate1 = AnimationUtils.loadAnimation(MovieDetail.this, R.anim.translate_right1);
                    button.startAnimation(animate1);
                    ObjectAnimator animation1 = ObjectAnimator.ofFloat(button, "translationX", 0.0f, 150f);
                    animation1.setDuration(300);
                    animation1.setInterpolator(new AccelerateDecelerateInterpolator());
                    animation1.start();
                    animate1.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            button.setVisibility(View.INVISIBLE);

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            button.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    check = 1;
                }
            }

            public void onSwipeRight() {
                if (check == 1) {
                    Animation animate = AnimationUtils.loadAnimation(MovieDetail.this, R.anim.translate_right);
                    card.startAnimation(animate);
                    ObjectAnimator animation = ObjectAnimator.ofFloat(card, "rotationY", 5f, 0f);
                    animation.setDuration(300);
                    animation.setInterpolator(new AccelerateDecelerateInterpolator());
                    animation.start();

                    Animation animate1 = AnimationUtils.loadAnimation(MovieDetail.this, R.anim.translate_left1);
                    button.startAnimation(animate1);
                    ObjectAnimator animation1 = ObjectAnimator.ofFloat(button, "translationX", 150f, 0f);
                    animation1.setDuration(300);
                    animation1.setInterpolator(new AccelerateDecelerateInterpolator());
                    animation1.start();
                    animate1.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            button.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    check = 0;
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                watchYoutubeVideo(MovieDetail.this,videoid);
            }
        });
    }
    public void initUI() {
        mainImage = findViewById(R.id.pic);
        backImage = findViewById(R.id.back);
        title = findViewById(R.id.title);
        release = findViewById(R.id.release);
        runtime = findViewById(R.id.runtime);
        revenue = findViewById(R.id.revenue);
        card = findViewById(R.id.card);
        button = findViewById(R.id.play);
        holder = findViewById(R.id.holder);
    }

}
