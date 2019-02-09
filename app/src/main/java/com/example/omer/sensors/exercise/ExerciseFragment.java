package com.example.omer.sensors.exercise;


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
import com.example.omer.sensors.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseFragment extends Fragment implements ExerciseContract.View {


    TextView timer, start;
    ExercisePresenter exercisePresenter;

    public ExerciseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        exercisePresenter = new ExercisePresenter(this, new ExerciseValidator(), new AccelerometerManager(sensorManager), ((MainActivity) getActivity()).getMediaPlayer());
        exercisePresenter.registerSensor();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                timer.setText("Next execerise");
                timer.setTextSize(22);
                timer.setEnabled(true);
                exercisePresenter.onCounterFinished();
            }

        }.start();
    }

    private void findViews(View view) {
        timer = view.findViewById(R.id.timer);
        timer.setEnabled(false);
        start = view.findViewById(R.id.startButton);
        start.setText("Exercise");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise, container, false);
    }

    @Override
    public void showValues(float x, float y, float z) {

    }
}
