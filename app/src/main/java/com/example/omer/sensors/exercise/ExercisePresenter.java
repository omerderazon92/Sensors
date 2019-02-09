package com.example.omer.sensors.exercise;

import com.example.omer.sensors.AccelerometerManager;
import com.example.omer.sensors.MusicContract;
import com.example.omer.sensors.ValuesListener;

public class ExercisePresenter {

    private ExerciseContract.View view;
    private ExerciseValidator exerciseValidator;
    private AccelerometerManager accelerometerManager;
    private MusicContract musicContract;

    public ExercisePresenter(ExerciseContract.View view, ExerciseValidator exerciseValidator, AccelerometerManager accelerometerManager, MusicContract musicContract) {
        this.view = view;
        this.exerciseValidator = exerciseValidator;
        this.accelerometerManager = accelerometerManager;
        this.musicContract = musicContract;
    }

    public void registerSensor() {
        musicContract.playMusic();
        accelerometerManager.startListeneing(new ValuesListener() {
            @Override
            public void onEventReceived(float x, float y, float z) {
                view.showValues(x, y, z);
                if (exerciseValidator.validateSqaut(x, y, z)) {
                    musicContract.playMusic();
                } else {
                    musicContract.stopMusic();
                }
            }
        });
    }

    public void unregisterSensor() {
        this.accelerometerManager.stopListening();
    }

    public void onCounterFinished() {
        unregisterSensor();
        musicContract.playMusic();
    }
}
