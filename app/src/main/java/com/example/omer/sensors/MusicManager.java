package com.example.omer.sensors;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicManager implements MusicContract {

    private MediaPlayer mediaPlayer;

    public MusicManager(Context context,int song) {
        mediaPlayer = MediaPlayer.create(context,song);
    }

    @Override
    public void playMusic() {
        if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    @Override
    public void stopMusic() {
       if (mediaPlayer.isPlaying()){
           mediaPlayer.pause();
       }
    }
}
