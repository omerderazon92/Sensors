package com.example.omer.sensors.boxing;

import com.example.omer.sensors.AccelerometerManager;
import com.example.omer.sensors.MusicContract;
import com.example.omer.sensors.ValuesListener;

public class BoxingPresenter {

    private BoxingContract.View view;
    private BoxingValidator boxingValidator;
    private AccelerometerManager accelerometerManager;
    private MusicContract musicContract;

    public BoxingPresenter(BoxingContract.View view, AccelerometerManager accelerometerManager, BoxingValidator squatValidator, MusicContract musicContract) {
        this.view = view;
        this.accelerometerManager = accelerometerManager;
        this.boxingValidator = squatValidator;
        this.musicContract = musicContract;
    }

    public void registerSensor() {
        musicContract.playMusic();
        accelerometerManager.startListeneing(new ValuesListener() {
            @Override
            public void onEventReceived(float x, float y, float z) {
                view.showValues(x, y, z);
                if (boxingValidator.validateSqaut(x, y, z)) {
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
