package com.example.omer.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerManager implements SensorEventListener{

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private ValuesListener valuesListener;

    public AccelerometerManager(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
        this.accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        valuesListener.onEventReceived(event.values[0],event.values[1],event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void startListeneing(ValuesListener valuesListener){
        sensorManager.registerListener(this,accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
        this.valuesListener = valuesListener;
    }

    public void stopListening(){
        sensorManager.unregisterListener(this);
        valuesListener = null;
    }
}
