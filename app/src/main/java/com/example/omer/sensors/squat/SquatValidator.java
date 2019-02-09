package com.example.omer.sensors.squat;

import android.util.Log;

public class SquatValidator {

    private static final float GRAVITY = (float) 9.8;
    private static final float MOVEMENT_TRESHHOLD = (float) 0.8;
    private static final float OTHER_AXES_MOVEMENT_TRESHHOLD = (float) 2;
    private static final float EDGE_OF_MOVEMENT_TRESHOLD = (float) 5;
    private static int stopCounter = 0;

    SquatValidator() {
    }

    private static final String TAG = "SquatValidator";

    public boolean validateSqaut(float x, float y, float z) {
        float noramlY = y - GRAVITY;
        Log.d(TAG, "validateSqaut() called with: x = [" + x + "], noramlY = [" + noramlY + "], z = [" + z + "]");
        //if the
        if (Math.abs(noramlY) > MOVEMENT_TRESHHOLD) {
            Log.d(TAG, "above movment threshold");
            if (Math.abs(x) < OTHER_AXES_MOVEMENT_TRESHHOLD && Math.abs(z) < OTHER_AXES_MOVEMENT_TRESHHOLD) {
                //indicates that phone is straight and there is a movemnt in y axe
                stopCounter = 0;
                return true;
            } else {
                stopCounter++;
            }
        } else {
            Log.d(TAG, "below movment threshold");
            stopCounter++;
        }
        return stopCounter <= EDGE_OF_MOVEMENT_TRESHOLD;
    }
}
