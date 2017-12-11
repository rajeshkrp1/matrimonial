package com.example.admin.metromonial.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.metromonial.R;

/**
 * Created by Admin on 11/28/2017.
 */

public class FragmentRecommentations extends Fragment {
    public FragmentRecommentations() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInseTanceState){

        View view=inflater.inflate(R.layout.fragment_recommentation,container,false);
        return  view;
    }
}
