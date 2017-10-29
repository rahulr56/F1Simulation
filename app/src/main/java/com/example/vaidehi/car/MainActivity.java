package com.example.vaidehi.car;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.R.attr.onClick;
import static android.os.SystemClock.sleep;

public class MainActivity extends AppCompatActivity {
    private Button accelerate;
    private ImageView car;
    boolean started, innerLoop;
    boolean left, right, top, bottom;
    int stepSize = 95;
    int trackSize = 35;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rahul);
        left = true;
        right = top = bottom = innerLoop = started = false;
        ImageView imSteering = findViewById(R.id.imageViewSteering);
        Button buttonAccelerate = findViewById(R.id.buttonAccelerate);
        Button  buttonBreak = findViewById(R.id.buttonBreak);
        Button buttonStartStop = findViewById(R.id.buttonStartStop);
        buttonBreak.setVisibility(View.INVISIBLE);
        buttonAccelerate.setVisibility(View.INVISIBLE);
        imSteering.setVisibility(View.INVISIBLE);

        boolean isInInnerLoop = false;

        buttonAccelerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG", "buttonAccelerate");
                RelativeLayout rrCar = new RelativeLayout(v.getContext());
                car = (ImageView) findViewById(R.id.imageViewCar);
                ImageView track = findViewById(R.id.imageViewTrack);
                float trackHeight = track.getHeight();
                float trackWidth = track.getWidth();
                Toast.makeText(getApplicationContext(), Float.toString(trackHeight)+" :H W: "+Float.toString(trackWidth), Toast.LENGTH_SHORT).show();

                if (left && !(bottom || top || right))
                {
                    Log.d("DEBUG", "Car position : "+Float.toString(car.getX()));
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
                }
            }
        });

        imSteering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView car = findViewById(R.id.imageViewCar);
                if(innerLoop)
                {
                    innerLoop = false;
                    if(bottom)
                    {
                        car.setY(car.getY() + trackSize);
                    }
                    else if(top)
                    {
                        car.setY(car.getY() - trackSize);
                    }
                    else if (right)
                    {
                        car.setX(car.getX() + trackSize);
                    }
                    else
                    {
                        car.setX(car.getX() - trackSize);
                    }

                }
                else
                {
                    innerLoop = true;
                    if(bottom)
                    {
                        car.setY(car.getY() - trackSize);
                    }
                    else if(top)
                    {
                        car.setY(car.getY() + trackSize);
                    }
                    else if (right)
                    {
                        car.setX(car.getX() - trackSize);
                    }
                    else
                    {
                        car.setX(car.getX() + trackSize);
                    }

                }
            }
        });


        buttonStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonAccelerate =
                        findViewById(R.id.buttonAccelerate);
                Button buttonBreak = findViewById(R.id.buttonBreak);
                ImageView imSteering = findViewById(R.id.imageViewSteering);
                if (started)
                {
                    started =  false;
                    buttonBreak.setVisibility(View.INVISIBLE);
                    buttonAccelerate.setVisibility(View.INVISIBLE);
                    imSteering.setVisibility(View.INVISIBLE);
                }
                else
                {
                    started = true;
                    buttonBreak.setVisibility(View.VISIBLE);
                    buttonAccelerate.setVisibility(View.VISIBLE);
                    imSteering.setVisibility(View.VISIBLE);
                }
            }
        });
     /*       buttonAccelerate.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                car = (ImageView) findViewById(R.id.imageViewCar);
                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        for(;event.getAction() == MotionEvent.ACTION_UP;) {
                            Log.d("DEBUG","Accelerating by 5..");
                            car.setY(car.getY() + 5);
                        }
                        break;
                    case MotionEvent.ACTION_UP: break;
                }
                return true;
            }
        });*/
    }
}