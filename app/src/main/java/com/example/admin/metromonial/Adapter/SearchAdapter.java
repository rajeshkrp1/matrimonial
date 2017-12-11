package com.example.admin.metromonial.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.metromonial.Fragment.FragmentMyProfile;
import com.example.admin.metromonial.Fragment.FragmentPreffred;
import com.example.admin.metromonial.Fragment.FragmentRecommentations;
import com.example.admin.metromonial.Fragment.FragmentSearch;

/**
 * Created by Admin on 11/28/2017.
 */

public class SearchAdapter extends FragmentPagerAdapter {
    public SearchAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        if(position==0){
            fragment= new FragmentSearch();

        }
        else if(position==1){
            fragment=new FragmentRecommentations();
        }
        else if(position==2){
            fragment=new FragmentPreffred();
        }
        else if(position==3){
            fragment=new FragmentMyProfile();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Search";
        }
        else if (position == 1)
        {
            title = "Recommentations(10)";
        }
        else if (position == 2)
        {
            title = "Preffred";
        }
        else if(position==3){
            title="My Profile";
        }
        return title;
    }
}
