package com.fourthwardmobile.colorchangeanimationdemo;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.animation.AnimatorListenerCompat;
import android.support.v4.animation.AnimatorUpdateListenerCompat;
import android.support.v4.animation.ValueAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ValueAnimator mValueAnimator;
    ImageView mImage;
    boolean mIsRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button)findViewById(R.id.animate_button);


        final int startColor = getResources().getColor(R.color.teal);
        final int endColor = getResources().getColor(R.color.green);
        mImage = (ImageView)findViewById(R.id.animate_image);

        //Change color of image using the "colorFilter" parameter
        mValueAnimator = ObjectAnimator.ofInt(mImage,"colorFilter",startColor,endColor);
        mValueAnimator.setDuration(1000);
        mValueAnimator.setEvaluator(new ArgbEvaluator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mIsRunning) {
                    mIsRunning = false;
                    button.setText(R.string.animate_button_text);
                    if(mValueAnimator.isRunning()) {

                        //Stop animation
                        Log.e("AnimationDemo", "Stop animation. Reset image color");
                        mValueAnimator.cancel();
                        //Reset color
                        mImage.setColorFilter(getResources().getColor(R.color.teal));
                    }
                } else {
                    mIsRunning = true;
                    button.setText(R.string.animage_button_text_stop);
                    //Start animation
                    mValueAnimator.start();
                }

            }
        });
    }
}
