package com.example.admin.metromonial.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.metromonial.Adapter.HomePageAdapter;
import com.example.admin.metromonial.Model.HomeModel;
import com.example.admin.metromonial.R;

import java.util.ArrayList;

/**
 * Created by Admin on 11/27/2017.
 */

public  class SearchActivity_multi extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recylerviewHome;
    private Context context;

    HomePageAdapter homePageAdapter;
    ImageView backArrow,advanceSearch;

    ArrayList<HomeModel> arrayList = new ArrayList<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.home_activity);

        backArrow= (ImageView) findViewById(R.id.leftarrow);
        advanceSearch= (ImageView) findViewById(R.id.headersett);
        advanceSearch.setOnClickListener(this);

        backArrow.setOnClickListener(this);
        context = SearchActivity_multi.this;

        recylerviewHome = (RecyclerView)findViewById(R.id.recylerviewHome);


        initmethos();


        homePageAdapter = new HomePageAdapter(arrayList,context);

        recylerviewHome.setLayoutManager(new LinearLayoutManager(context));

        recylerviewHome.setAdapter(homePageAdapter);

    }


    public  void initmethos(){

        for (int i = 0 ; i<10;i++) {

            HomeModel homeModel = new HomeModel("Sheetal Srivastava", R.drawable.l);

            arrayList.add(homeModel);

    }}


    @Override
    public void onClick(View view) {
        switch ((view.getId())) {

            case R.id.leftarrow:
        super.onBackPressed();
        break;

            case R.id.headersett:
                Intent intent=new Intent(this,AdvanceSearchActivity.class);
                startActivity(intent);
                break;
    }
    }
}

