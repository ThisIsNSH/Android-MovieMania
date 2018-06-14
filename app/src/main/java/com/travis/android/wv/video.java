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
        String viewSource ="http://dl1.upload08.com/files/Film/2018/Backstabbing%20for%20Beginners%202018/Backstabbing.for.Beginners.2018.720p.BRrip.YIFY.FardaDL.mkv";
        mVideoView.setVideoURI(Uri.parse(viewSource));
    }
}
