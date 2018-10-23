package com.honnold.rikki.sounds;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ImageView birdie;
    private int whichBird = 0;
    private String PREFIX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PREFIX = "android.resource://"+getPackageName()+"/";
        birdie = (ImageView) findViewById(R.id.imageBird);
        mediaPlayer = MediaPlayer.create(this, R.raw.cardinal);
        //mediaPlayer.setLooping(true);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                birdie.setClickable(true);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                mediaPlayer.stop();
                mediaPlayer.reset();

                birdie.setClickable(false);

                Uri sound;
                switch (whichBird) {
                    case 0:
                        whichBird++;
                        birdie.setImageResource(R.drawable.chickadee);
                        sound = Uri.parse(PREFIX + R.raw.chickadee);
                        break;

                    case 1:
                        whichBird++;
                        birdie.setImageResource(R.drawable.robin);
                        sound = Uri.parse(PREFIX + R.raw.robin);
                        break;

                    case 2:
                        whichBird++;
                        birdie.setImageResource(R.drawable.birds);
                        sound = Uri.parse(PREFIX + R.raw.birds);
                        break;

                    case 3:
                        whichBird = 0;
                        birdie.setImageResource(R.drawable.cardinal);
                        sound = Uri.parse(PREFIX + R.raw.cardinal);
                        break;

                    default:
                        birdie.setImageResource(R.drawable.birds);
                        sound = Uri.parse(PREFIX + R.raw.birds);
                        break;
                }

                try {
                    mediaPlayer.setDataSource(getApplicationContext(),
                            sound);
                    mediaPlayer.prepareAsync();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void playSound(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }
}
