package com.zenlabs.z5x5.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zenlabs.z5x5.CustomView.StrechVideoView;
import com.zenlabs.z5x5.R;


public class SplashActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        StrechVideoView introVideo = (StrechVideoView) this.findViewById(R.id.videoView);
        introVideo.setOnCompletionListener(this);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.opener;
        introVideo.setVideoURI(Uri.parse(path));
        introVideo.start();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
        finish();
    }
}
