package com.example.omer.sensors;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.omer.sensors.boxing.BoxingFragment;
import com.example.omer.sensors.exercise.ExerciseFragment;
import com.example.omer.sensors.squat.SquatFragment;

public class MainActivity extends AppCompatActivity {

    private MusicContract mediaPlayer;
    private TextView textView, letGoButton;
    private int chosenSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.immigrant_song);
        letGoButton = findViewById(R.id.letsGoButton);
        letGoButton.setEnabled(false);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenSong = R.id.immigrant_song;
                textView.setTextColor(getResources().getColor(R.color.Orange));
                letGoButton.setEnabled(true);
                chosenSong = R.raw.immigrant_song;
            }
        });

        letGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = new MusicManager(MainActivity.this, chosenSong);
                mediaPlayer.stopMusic();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new SquatFragment()).commit();
            }
        });

        Window window = getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.Orange));
        }
    }

    public MusicContract getMediaPlayer() {
        return mediaPlayer;
    }

    public void moveToBoxingExercise() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new BoxingFragment()).commit();
    }

    public void moveToExerciseFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new ExerciseFragment()).commit();
    }
}
