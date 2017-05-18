package com.edwinacubillos.videorecorder;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    VideoView video;
    String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = (VideoView) findViewById(R.id.surfaceView);
    }

    public void grabar(View view) {
       /* //activar la camara
        name = Environment.getExternalStorageDirectory()+"/video.mp4";
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        Uri salida = Uri.fromFile(new File(name));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, salida);*/

       //Cargar Videos de galeria
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri = data.getData();
        video.setVideoURI(uri);
        video.setMediaController(new MediaController(this));
        video.start();
    }
}
