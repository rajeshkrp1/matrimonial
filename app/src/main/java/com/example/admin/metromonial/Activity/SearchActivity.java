package com.example.admin.metromonial.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.metromonial.Adapter.SearchAdapter;
import com.example.admin.metromonial.R;

/**
 * Created by Admin on 11/28/2017.
 */

public class SearchActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        tabLayout= (TabLayout) findViewById(R.id.tab_layout);

        viewPager= (ViewPager) findViewById(R.id.pager);
        SearchAdapter mSearchAdapter=new SearchAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mSearchAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
