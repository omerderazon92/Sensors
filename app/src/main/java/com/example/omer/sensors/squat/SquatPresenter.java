package com.example.omer.sensors.squat;

import com.example.omer.sensors.AccelerometerManager;
import com.example.omer.sensors.MusicContract;
import com.example.omer.sensors.ValuesListener;

public class SquatPresenter {

    private SquatContract.View view;
    private SquatValidator squatValidator;
    private AccelerometerManager accelerometerManager;
    private MusicContract musicContract;


    public SquatPresenter(SquatContract.View view, AccelerometerManager accelerometerManager, SquatValidator squatValidator, MusicContract musicContract) {
        this.view = view;
        this.accelerometerManager = accelerometerManager;
        this.squatValidator = squatValidator;
        this.musicContract = musicContract;
    }

    public void registerSensor() {
        accelerometerManager.startListeneing(new ValuesListener() {
            @Override
            public void onEventReceived(float x, float y, float z) {
                view.showValues(x, y, z);
                if (squatValidator.validateSqaut(x, y, z)) {
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
