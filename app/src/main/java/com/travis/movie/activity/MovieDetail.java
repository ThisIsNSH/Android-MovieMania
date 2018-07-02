package com.travis.movie.activity;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.jgabrielfreitas.core.BlurImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.travis.movie.R;
import com.travis.movie.extra.OnSwipeTouchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.travis.movie.activity.GlideOptions.bitmapTransform;

public class MovieDetail extends AppCompatActivity {

    String id, poster, duration, release_date, moviename, movierevenue, movierating;
    RelativeLayout holder;
    String videoid;
    CardView card;
    FrameLayout button;
    int check = 0;
    TextView title, runtime, release, revenue;
    ImageView mainImage, movieposter;
    ImageView backImage,background;
    RatingBar rating;
    RequestQueue movielist;
    float movievote;

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

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id", "00000000");

        movieposter =  findViewById(R.id.pic);
        runtime = findViewById(R.id.runtime);
        release =  findViewById(R.id.release);
        movielist = Volley.newRequestQueue(this);
        title = findViewById(R.id.title);
        backImage = findViewById(R.id.back);
        revenue = findViewById(R.id.revenue);
        rating = findViewById(R.id.ratingBar);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, "http://api.themoviedb.org/3/movie/" + id + "?api_key=c94d74f77ae9409c43d2d3d74a1c5d3f&append_to_response=videos", null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            duration = response.getString("runtime");
                            moviename = response.getString("title");
                            release_date = response.getString("release_date");
                            int mrevenue = response.getInt("revenue") / 1000000;
                            movievote = (response.getInt("vote_average")) / 2;
                            rating.setRating(movievote);
//                            rating.setNumStars(movievote);
                            poster = response.getString("poster_path");
                            String month;
                            String[] split = release_date.split("-");
                            switch (split[1]) {
                                case "01":
                                    month = "Jan";
                                    break;
                                case "02":
                                    month = "Feb";
                                    break;
                                case "03":
                                    month = "Mar";
                                    break;
                                case "04":
                                    month = "Apr";
                                    break;
                                case "05":
                                    month = "May";
                                    break;
                                case "06":
                                    month = "Jun";
                                    break;
                                case "07":
                                    month = "Jul";
                                    break;
                                case "08":
                                    month = "Aug";
                                    break;
                                case "09":
                                    month = "Sep";
                                    break;
                                case "10":
                                    month = "Oct";
                                    break;
                                case "11":
                                    month = "Nov";
                                    break;
                                case "12":
                                    month = "Dec";
                                    break;
                                default:
                                    month = "";
                                    break;
                            }
                            release.setText(split[2] + " " + month);
                            Picasso.get().load("http://image.tmdb.org/t/p/original/" + poster).into(movieposter);
                            GlideApp.with(MovieDetail.this)
                                    .load("http://image.tmdb.org/t/p/original/" + poster)
                                    .apply(bitmapTransform(new BlurTransformation(4, 3)))
                                    .into(backImage);
                            runtime.setText(duration + " Mins");
                            title.setText(moviename);
                            revenue.setText(" | $" + mrevenue + " M");
                            List<String> allNames = new ArrayList<String>();
                            JSONObject jsonObj = response.getJSONObject("videos");
                            JSONArray cast = jsonObj.getJSONArray("results");
                            JSONObject trailor = cast.getJSONObject(cast.length() - 1);
                            videoid = trailor.getString("key");
                            allNames.add(videoid);

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
                watchYoutubeVideo(MovieDetail.this, videoid);
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
    public void webapp(View V)
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_movie_detail);
        title.setText("About Movie!");
        // Set On ClickListener
        title.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
              dialog.show();
            }
        });
    }
}
