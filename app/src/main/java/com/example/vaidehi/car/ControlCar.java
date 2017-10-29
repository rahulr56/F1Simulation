package com.example.vaidehi.car;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import static com.example.vaidehi.car.MainActivity.accelerated;
import static com.example.vaidehi.car.MainActivity.bottom;
import static com.example.vaidehi.car.MainActivity.left;
import static com.example.vaidehi.car.MainActivity.right;
import static com.example.vaidehi.car.MainActivity.stepSize;
import static com.example.vaidehi.car.MainActivity.top;
import static com.example.vaidehi.car.MainActivity.trackHeight;
import static com.example.vaidehi.car.MainActivity.trackWidth;
import static java.lang.Thread.sleep;

public class ControlCar extends AsyncTask<Void, Void, Void> {
    private MainActivity activity;
    // float trackWidth, trackHeight;

    public ControlCar(MainActivity activity, int trackHeight, int trackWidth) {
        this.activity = activity;
//        this.trackHeight = trackHeight;
//        this.trackWidth = trackWidth;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ImageView car = activity.findViewById(R.id.imageViewCar);
//        int trackHeight = outerTrackHEnd - outerTrackHStart;
//        int trackWidth = outerTrackWEnd - outerTrackWEnd;
        Log.d("DEBUG", "Height : " + Integer.toString((int) trackHeight) + " Width : " + Integer.toString((int) trackWidth));

        while (true) {
            if (accelerated) {

                if (left && !(bottom || top || right)) {
                    Log.d("DEBUG", "Car position : " + Float.toString(car.getY()));
                    Log.d("DEBUG", "Height : " + Integer.toString((int) trackHeight) + " Width : " + Integer.toString((int) trackWidth));
                    car.setY(car.getY() + stepSize);
                    if (car.getY() + stepSize > trackHeight) {
                        left = false;
                        bottom = true;
                    }
                } else if (bottom && !(top || right || left)) {
                    Log.d("DEBUG", "Car position : " + Float.toString(car.getX()));
                    Log.d("DEBUG", "Height : " + Integer.toString((int) trackHeight) + " Width : " + Integer.toString((int) trackWidth));
                    car.setX(car.getX() + stepSize);
                    if (car.getX() + stepSize >= trackWidth) {
                        bottom = false;
                        right = true;
                    }
                } else if (right && !(bottom || top || left)) {
                    Log.d("DEBUG", "Car position : " + Float.toString(car.getY()));
                    Log.d("DEBUG", "Height : " + Integer.toString((int) trackHeight) + " Width : " + Integer.toString((int) trackWidth));
                    car.setY(car.getY() - stepSize);
                    if (car.getY() - stepSize < 0) {   // top of the track
                        right = false;
                        top = true;
                    }
                } else if (top && !(bottom || right || left)) {
                    Log.d("DEBUG", "Car position : " + Float.toString(car.getX()));
                    Log.d("DEBUG", "Height : " + Integer.toString((int) trackHeight) + " Width : " + Integer.toString((int) trackWidth));
                    car.setX(car.getX() - stepSize);
                    if (car.getX() - stepSize <= 0) {
                        top = false;
                        left = true;
                    }
                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            else
//            {
//                Log.d("DEBUG","Waiting to start the vehicle");
//            }
        }
    }
}
