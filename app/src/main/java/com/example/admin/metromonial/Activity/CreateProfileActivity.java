package com.example.admin.metromonial.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.admin.metromonial.R;

/**
 * Created by RAJESH on 12/8/2017.
 */

public class CreateProfileActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView create_basicInfo,update_religious,update_family,partner_location_update;
    LinearLayout basic_info_layout,religious_layout,family_layout,partner_location_layout;


    Button btnDone;




    protected void onCreate(Bundle savedInseTanceState){
        super.onCreate(savedInseTanceState);
        setContentView(R.layout.activity_create_profile);

        basic_info_layout= (LinearLayout) findViewById(R.id.basicInfo_layout);
        create_basicInfo= (ImageView) findViewById(R.id.create_basic_info);
        update_religious= (ImageView) findViewById(R.id.religious_status);
        religious_layout= (LinearLayout) findViewById(R.id.religious_layout);
        update_family= (ImageView) findViewById(R.id.family_update);
        family_layout= (LinearLayout) findViewById(R.id.family_layout);
        partner_location_update= (ImageView) findViewById(R.id.partner_l_detail);
        btnDone= (Button) findViewById(R.id.btn_create_profile);
        partner_location_layout= (LinearLayout) findViewById(R.id.partner_location_layout);

        create_basicInfo.setOnClickListener(this);
        update_religious.setOnClickListener(this);
        update_family.setOnClickListener(this);
        partner_location_update.setOnClickListener(this);
        btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.create_basic_info:
                basic_info_layout.setVisibility(View.VISIBLE);
                break;

            case R.id.religious_status:
                religious_layout.setVisibility(View.VISIBLE);
                break;
            case R.id.family_update:
                family_layout.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_create_profile:
                Intent intent=new Intent(this,MeilleureCouple.class);
                startActivity(intent);
                break;



        }
    }
}
