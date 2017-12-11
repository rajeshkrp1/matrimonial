package com.example.admin.metromonial.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.admin.metromonial.Adapter.SlidingImage_Adapter;
import com.example.admin.metromonial.MainActivity;
import com.example.admin.metromonial.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Admin on 11/23/2017.
 */

public class After_spalshActivity extends AppCompatActivity {



    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.back,R.drawable.backone,R.drawable.backtwo};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    Button button_serchguest;
    Button login_btn, register_btn;
    Context context;
     boolean ischecked = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = After_spalshActivity.this;
        init();
    }
    private void init() {

        for(int i=0;i<IMAGES.length;i++) ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);

        button_serchguest = (Button)findViewById(R.id.button_serchguest);

        login_btn = (Button)findViewById(R.id.loginbtn) ;

        register_btn = (Button)findViewById(R.id.registerBtn);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login_btn.setBackgroundResource(R.color.colorPrimary);

                login_btn.setTextColor(context.getResources().getColor(R.color.white));

                register_btn.setBackgroundResource(R.color.white);

                register_btn.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                Intent i = new Intent(After_spalshActivity.this, LoginActivity.class);

                startActivity(i);
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_btn.setBackgroundResource(R.color.colorPrimary);
                register_btn.setTextColor(context.getResources().getColor(R.color.white));
                login_btn.setBackgroundResource(R.color.white);
                login_btn.setTextColor(context.getResources().getColor(R.color.colorPrimary));

                Intent i = new Intent(After_spalshActivity.this,RegisterActivity.class);
                startActivity(i);

            }
        });
        button_serchguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(After_spalshActivity.this, Serach_NowGuest.class);

                startActivity(i);

                finish();

            }
        });
        mPager.setAdapter(new SlidingImage_Adapter(After_spalshActivity.this,ImagesArray));
        CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(3 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();

        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {


            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }



}
