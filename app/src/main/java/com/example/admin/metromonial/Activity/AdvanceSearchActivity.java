package com.example.admin.metromonial.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.metromonial.R;

/**
 * Created by RAJESH on 12/6/2017.
 */

public class AdvanceSearchActivity extends AppCompatActivity {


    Spinner spinnerCountry,spinnerProvince,spinnerOccupation,spinnerEducation,spinnerMsataus,spinnerReligion;

    String spinnerCountry_name[] = {"India", "India", "India", "India", "India", "India", "India", "India", "India"};

    String spinnerProvince_name[] = {"India", "India", "India", "India", "India", "India", "India", "India", "India"};

    String spinnerReligion_name[] = {"India", "India", "India", "India", "India", "India", "India", "India", "India"};


    protected void onCreate(Bundle savedInseTanceState){
        super.onCreate(savedInseTanceState);
        setContentView(R.layout.activity_advance_search);


        spinnerCountry= (Spinner) findViewById(R.id.spinnerCountry);
        spinnerProvince= (Spinner) findViewById(R.id.spinnerProvince);
        spinnerOccupation= (Spinner) findViewById(R.id.spinnerOccupation);
        spinnerEducation= (Spinner) findViewById(R.id.spinnerEducation);
        spinnerMsataus= (Spinner) findViewById(R.id.spinnerMsataus);
        spinnerReligion= (Spinner) findViewById(R.id.spinnerReligion);
        Toast.makeText(this, "dsfsf", Toast.LENGTH_SHORT).show();




        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,R.layout.custom_spinner_layout,spinnerCountry_name );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textview);
        spinnerCountry.setAdapter(spinnerArrayAdapter);



        ArrayAdapter<String> spinnerProvinceArrayAdapter = new ArrayAdapter<String>(this,R.layout.custom_spinner_layout,spinnerProvince_name );
        spinnerProvinceArrayAdapter.setDropDownViewResource(R.layout.spinner_textview);
        spinnerProvince.setAdapter(spinnerProvinceArrayAdapter);



        ArrayAdapter<String> spinnerReligionArrayAdapter = new ArrayAdapter<String>(this,R.layout.custom_spinner_layout,spinnerReligion_name );
        spinnerReligionArrayAdapter.setDropDownViewResource(R.layout.spinner_textview);
        spinnerReligion.setAdapter(spinnerReligionArrayAdapter);





        ArrayAdapter<String> spinnerOccupationArrayAdapter = new ArrayAdapter<String>(this,R.layout.custom_spinner_layout,spinnerCountry_name );
        spinnerOccupationArrayAdapter.setDropDownViewResource(R.layout.spinner_textview);
        spinnerOccupation.setAdapter(spinnerArrayAdapter);



        ArrayAdapter<String> spinnerEducationArrayAdapter = new ArrayAdapter<String>(this,R.layout.custom_spinner_layout,spinnerProvince_name );
        spinnerEducationArrayAdapter.setDropDownViewResource(R.layout.spinner_textview);
        spinnerEducation.setAdapter(spinnerProvinceArrayAdapter);



        ArrayAdapter<String> spinnerMsatausArrayAdapter = new ArrayAdapter<String>(this,R.layout.custom_spinner_layout,spinnerReligion_name );
        spinnerMsatausArrayAdapter.setDropDownViewResource(R.layout.spinner_textview);
        spinnerMsataus.setAdapter(spinnerReligionArrayAdapter);




    }
}
