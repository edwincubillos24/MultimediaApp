package com.edwinacubillos.audiorecorder;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    MediaRecorder recorder;
    MediaPlayer player;
    File archivo;
    Button bGrabar, bReproducir, bDetener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bGrabar = (Button) findViewById(R.id.bGrabar);
        bReproducir = (Button) findViewById(R.id.bReproducir);
        bDetener = (Button) findViewById(R.id.bDetener);
    }

    public void grabar(View view) {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        File path = new File(Environment.getExternalStorageDirectory().getPath());

        try {
            archivo = File.createTempFile("temporal",".3gp",path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.setOutputFile(archivo.getAbsolutePath());

        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        recorder.start();
        bGrabar.setEnabled(false);
        bDetener.setEnabled(true);
    }

    public void detener(View view) {
        recorder.stop();
        recorder.release();
        player = new MediaPlayer();

        try {
            player.setDataSource(archivo.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.setOnCompletionListener(this);
        bDetener.setEnabled(false);
        bGrabar.setEnabled(true);
        bReproducir.setEnabled(true);
    }

    public void reproducir(View view) {
        player.start();
        bGrabar.setEnabled(false);
        bReproducir.setEnabled(false);
        bDetener.setEnabled(false);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        bGrabar.setEnabled(true);
        bReproducir.setEnabled(true);
        bDetener.setEnabled(true);
    }
}
