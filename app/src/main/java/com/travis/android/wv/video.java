package com.travis.android.wv;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by LENOVO on 10-06-2018.
 */

public class video extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        VideoView mVideoView = (VideoView) findViewById(R.id.video);
        mVideoView.setMediaController(new MediaController(this));
        String viewSource ="http://wapbestmovie.xyz/load/Full_Movies/Bollywood_Movies_2018/Padman%202018%20Dvdrip/MP4%20HQ/Padman%202018%20Dvdrip%20Hq%20Part1.mp4";
        mVideoView.setVideoURI(Uri.parse(viewSource));
    }
}
