package com.example.omer.sensors.boxing;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class BoxingValidator {

    private static final float GRAVITY = (float) 9.8;
    private static final float MOVEMENT_TRESHHOLD = (float) 3.2;
    private static final float OTHER_AXES_MOVEMENT_TRESHHOLD = (float) 2;
    private static final float EDGE_OF_MOVEMENT_TRESHOLD = (float) 7;
    private static int stopCounter = 0;

    public boolean validateSqaut(float x, float y, float z) {
        float noramlY = y - GRAVITY;
        Log.d(TAG, "validateSqaut() called with: x = [" + x + "], noramlY = [" + noramlY + "], z = [" + z + "]");
        //if the
        if (Math.abs(x) > MOVEMENT_TRESHHOLD) {
            Log.d(TAG, "above movment threshold");
            if (Math.abs(noramlY) < OTHER_AXES_MOVEMENT_TRESHHOLD &&
            Math.abs(z) < OTHER_AXES_MOVEMENT_TRESHHOLD){
                //indicates that phone is straight and there is a movemnt in y axe
                stopCounter = 0;
                return true;
            } else{
                stopCounter++;
            }
        } else {
            Log.d(TAG, "below movment threshold");
            stopCounter++;
        }
        return stopCounter <= EDGE_OF_MOVEMENT_TRESHOLD;
    }
}

