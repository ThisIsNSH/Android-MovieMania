package com.travis.android.Movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
    }