package com.example.vaidehi.car;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static boolean started, innerLoop, accelerated, breaked;
    static boolean left, right, top, bottom;
    static int stepSize = 25;
    static int trackSize = 35;
    static int outerTrackHStart, outerTrackHEnd, innerTrackHStart, innerTrackHEnd;
    static int outerTrackWStart, outerTrackWEnd, innerTrackWStart, innerTrackWEnd;
    static float trackHeight, trackWidth;
    int boundary = 35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rahul);

        left = true;
        right = top = bottom = innerLoop = started = false;
        accelerated = false;
        breaked = true;
        ImageView imSteering = findViewById(R.id.imageViewSteering);
        ImageView track = findViewById(R.id.imageViewTrack);
        Button buttonAccelerate = findViewById(R.id.buttonAccelerate);
        Button  buttonBreak = findViewById(R.id.buttonBreak);
        Button buttonStartStop = findViewById(R.id.buttonStartStop);

        buttonBreak.setVisibility(View.INVISIBLE);
        buttonAccelerate.setVisibility(View.INVISIBLE);
        imSteering.setVisibility(View.INVISIBLE);
        trackHeight = track.getHeight();
        trackWidth = track.getWidth();

        boolean isInInnerLoop = false;
        new ControlCar(this, (int) trackHeight, (int) trackWidth).execute();
        buttonAccelerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accelerated = true;
                breaked = false;
                ImageView track = findViewById(R.id.imageViewTrack);
                outerTrackHStart = boundary;
                outerTrackHEnd = track.getHeight() - boundary;
                innerTrackHStart = outerTrackHStart + boundary;
                innerTrackHEnd = outerTrackHEnd - boundary;
                trackHeight = outerTrackHEnd - outerTrackHStart;

                outerTrackWStart = boundary;
                outerTrackWEnd = track.getWidth() - boundary;
                innerTrackWStart = outerTrackWStart + boundary;
                innerTrackWEnd = outerTrackWEnd - boundary;
                trackWidth = outerTrackWEnd - outerTrackWStart;
                Log.d("TRACK DETAILS", "Image Height" + Float.toString(track.getHeight()));
                Log.d("TRACK DETAILS", "Image Width" + Float.toString(track.getWidth()));
                Log.d("TRACK DETAILS", "Outer Height Start" + Integer.toString(outerTrackHStart));
                Log.d("TRACK DETAILS", "Outer Height Start" + Integer.toString(outerTrackHStart));
                Log.d("TRACK DETAILS", "Outer Height End" + Integer.toString(outerTrackHEnd));
                Log.d("TRACK DETAILS", "Outer Width Start" + Integer.toString(outerTrackWStart));
                Log.d("TRACK DETAILS", "Outer Width End" + Integer.toString(outerTrackWEnd));
 /*               ImageView car = (ImageView) findViewById(R.id.imageViewCar);

                float trackHeight = track.getHeight();
                float trackWidth = track.getWidth();

                Log.d("IN TRACK DETAILS","Image Height"+Float.toString(track.getHeight()));
                Log.d("IN TRACK DETAILS","Image Width"+Float.toString(track.getWidth()));
                Toast.makeText(getApplicationContext(), Float.toString(trackHeight)+" :H W: "+Float.toString(trackWidth), Toast.LENGTH_SHORT).show();
               if (left && !(bottom || top || right))
                {
//                    Log.d("DEBUG", "Car position : "+Float.toString(car.getX()));
                    car.setY(car.getY() + stepSize);
                    if(car.getY() + stepSize > trackHeight)
                    {
                        left = false;
                        bottom = true;
                    }
                }
                else if (bottom && !(top|| right || left ))
                {
                    car.setX(car.getX() + stepSize);
                    if (car.getX() + stepSize >= trackWidth)
                    {
                        bottom = false;
                        right = true;
                    }
                }
                else if (right && !(bottom || top || left ))
                {
                    car.setY(car.getY() - stepSize);
                    if(car.getY() - stepSize < 0) {   // top of the track
                        right = false;
                        top = true;
                    }
                }
                else if(top && !(bottom || right || left))
                {
                    car.setX(car.getX() - stepSize);
                    if(car.getX() - stepSize <= 0)
                    {
                        top = false;
                        left = true;
                    }
                }*/
            }
        });
        imSteering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView car = findViewById(R.id.imageViewCar);

                if (innerLoop) {
                    innerLoop = false;
                    if (bottom) {
                        car.setY(car.getY() + trackSize);
                    } else if (top) {
                        car.setY(car.getY() - trackSize);
                    } else if (right) {
                        car.setX(car.getX() + trackSize);
                    } else {
                        car.setX(car.getX() - trackSize);
                    }
                } else {
                    innerLoop = true;
                    if (bottom) {
                        car.setY(car.getY() - trackSize);
                    } else if (top) {
                        car.setY(car.getY() + trackSize);
                    } else if (right) {
                        car.setX(car.getX() - trackSize);
                    } else {
                        car.setX(car.getX() + trackSize);
                    }
                }
            }
        });

        buttonBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breaked = true;
                accelerated = false;
            }
        });

        buttonStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonAccelerate =
                        findViewById(R.id.buttonAccelerate);
                Button buttonBreak = findViewById(R.id.buttonBreak);
                ImageView imSteering = findViewById(R.id.imageViewSteering);
                if (started) {
                    started = false;
                    buttonBreak.setVisibility(View.INVISIBLE);
                    buttonAccelerate.setVisibility(View.INVISIBLE);
                    imSteering.setVisibility(View.INVISIBLE);
                } else {
                    started = true;
                    buttonBreak.setVisibility(View.VISIBLE);
                    buttonAccelerate.setVisibility(View.VISIBLE);
                    imSteering.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}

