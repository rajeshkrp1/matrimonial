package com.example.admin.metromonial.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.admin.metromonial.R;

/**
 * Created by Admin on 11/28/2017.
 */

public class FragmentSearch extends Fragment {

    Spinner spinnerCountry, spinnerProvince, spinnerReligion;
    Context context;
    Button btnSubmit;

    String spinnerCountry_name[] = {"India", "India", "India", "India", "India", "India", "India", "India", "India"};

    String spinnerProvince_name[] = {"India", "India", "India", "India", "India", "India", "India", "India", "India"};

    String spinnerReligion_name[] = {"India", "India", "India", "India", "India", "India", "India", "India", "India"};



    public FragmentSearch() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInseTanceState){

        View view=inflater.inflate(R.layout.fragment_search,container,false);

        context=getActivity();
        spinnerCountry = (Spinner)view.findViewById(R.id.spinnerCountry);
        spinnerProvince = (Spinner)view.findViewById(R.id.spinnerProvince);
        spinnerReligion = (Spinner)view.findViewById(R.id.spinnerReligion);
        btnSubmit=(Button)view.findViewById(R.id.fbtnubmit);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context,R.layout.custom_spinner_layout,spinnerCountry_name );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textview);
        spinnerCountry.setAdapter(spinnerArrayAdapter);



        ArrayAdapter<String> spinnerProvinceArrayAdapter = new ArrayAdapter<String>(context,R.layout.custom_spinner_layout,spinnerProvince_name );
        spinnerProvinceArrayAdapter.setDropDownViewResource(R.layout.spinner_textview);
        spinnerProvince.setAdapter(spinnerProvinceArrayAdapter);



        ArrayAdapter<String> spinnerReligionArrayAdapter = new ArrayAdapter<String>(context,R.layout.custom_spinner_layout,spinnerReligion_name );
        spinnerReligionArrayAdapter.setDropDownViewResource(R.layout.spinner_textview);
        spinnerReligion.setAdapter(spinnerReligionArrayAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(context,)
            }
        });
        return  view;
    }
}
