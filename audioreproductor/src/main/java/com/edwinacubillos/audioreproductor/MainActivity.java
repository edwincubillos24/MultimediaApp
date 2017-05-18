package com.edwinacubillos.audioreproductor;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View view) {
        mp = MediaPlayer.create(MainActivity.this, R.raw.morethanwords);
        mp.start();
    }

    public void stop(View view) {
        mp.stop();
        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playpause(View view) {
        if (mp.isPlaying()){
            mp.pause();
        } else {
            mp.start();
        }
    }
}
