package com.example.admin.metromonial.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.metromonial.R;

/**
 * Created by Admin on 11/23/2017.
 */

public class splash extends AppCompatActivity {

    Handler handler;

    ImageView laoder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.spalsh);

        laoder = (ImageView)findViewById(R.id.loder);

        move(laoder);



/*

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setProgress(20);

        progressBar.setSecondaryProgress(50);
*/


//        GifImageView gifImageView = (GifImageView) findViewById(R.id.gifimageview);
//        gifImageView.setGifImageResource(R.drawable.splashgip);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    Thread.sleep(2500);

                }

                catch (InterruptedException e) {

                    e.printStackTrace();

                }

                Intent i = new Intent(splash.this, After_spalshActivity.class);

                startActivity(i);

                finish();
            }
        }
        )
                .start();
    }

    public void move(View view){
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        laoder.startAnimation(animation1);
    }}


