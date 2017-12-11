package com.example.admin.metromonial.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.metromonial.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by RAJESH on 12/6/2017.
 */




public class MeilleureCouple extends AppCompatActivity implements View.OnClickListener {
    de.hdodenhof.circleimageview.CircleImageView profile_icon;
    ImageView backArrow;

    protected void onCreate(Bundle savedInseTanceState){
    super.onCreate(savedInseTanceState);
    setContentView(R.layout.activity_meilleure_couple);


    profile_icon= (CircleImageView) findViewById(R.id.user_profile_image);
    backArrow= (ImageView) findViewById(R.id.leftarrow);


        profile_icon.setOnClickListener(this);
        backArrow.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_profile_image:
                Intent intent=new Intent(this,MyProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.leftarrow:
                onBackPressed();
                break;

        }
    }
}
