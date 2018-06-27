package com.travis.movie.extra;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.travis.movie.R;

/**
 * Created by Abhinav on 10-06-2018.
 */

public class video extends AppCompatActivity {

    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        pb = findViewById(R.id.pb);
        final String viewSource = "http://dl1.upload08.com/files/Film/2018/Backstabbing%20for%20Beginners%202018/Backstabbing.for.Beginners.2018.720p.BRrip.YIFY.FardaDL.mkv";
        final VideoView mVideoView = findViewById(R.id.video);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.setVideoURI(Uri.parse(viewSource));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();
                pb.setVisibility(View.GONE);
            }
        });
    }
}
