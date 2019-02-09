package com.example.omer.sensors.squat;


import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omer.sensors.AccelerometerManager;
import com.example.omer.sensors.MainActivity;
import com.example.omer.sensors.MusicManager;
import com.example.omer.sensors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SquatFragment extends Fragment implements SquatContract.View {

    TextView sensorValues, timer, start;
    SquatPresenter squatPresenter;

    public SquatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        squatPresenter = new SquatPresenter(this, new AccelerometerManager(sensorManager), new SquatValidator(), ((MainActivity)getActivity()).getMediaPlayer());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_squat, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setText("Squat");
                squatPresenter.registerSensor();
                start.setEnabled(false);
                new CountDownTimer(60000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        timer.setText(String.valueOf(millisUntilFinished / 1000));
                    }

                    public void onFinish() {
                        timer.setText("Next exercise");
                        timer.setTextSize(22);
                        timer.setEnabled(true);
                        squatPresenter.onCounterFinished();
                    }

                }.start();
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).moveToBoxingExercise();
            }
        });
    }

    private void findViews(View view) {
        sensorValues = view.findViewById(R.id.values);
        sensorValues.setVisibility(View.INVISIBLE);
        timer = view.findViewById(R.id.timer);
        timer.setEnabled(false);
        start = view.findViewById(R.id.startButton);
    }

    @Override
    public void showValues(float x, float y, float z) {
        sensorValues.setText("x:" + x + " y:" + y + " z:" + z);
    }
}
