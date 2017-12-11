package com.example.admin.metromonial.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appyvet.rangebar.RangeBar;
import com.example.admin.metromonial.NetworkConnection.FileUploadInterface;
import com.example.admin.metromonial.NetworkConnection.RetrofitHandler;
import com.example.admin.metromonial.R;
import com.example.admin.metromonial.Utils.AppConstants;
import com.example.admin.metromonial.Utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 11/23/2017.
 */

public class Serach_NowGuest extends AppCompatActivity {

    ImageView leftarrow;

    Button male_btn, femal_btn;
    Button Submit;
    RangeBar rangeBar;
    private Context context;
    TextView txt_login, txt_register;

    Dialog mDialog;
    private String ResigterResponse="";


    Spinner spinnerCountry, spinnerProvince, spinnerReligion;

    LinearLayout malesearch, femalesearch;

    String spinnerCountry_name[] = {"India", "India", "India", "India", "India", "India", "India", "India", "India"};

    String spinnerProvince_name[] = {"India", "India", "India", "India", "India", "India", "India", "India", "India"};

    String spinnerReligion_name[] = {"India", "India", "India", "India", "India", "India", "India", "India", "India"};


    ArrayList<List>arrayList;

    String looking_for, sage, eage,  country,  state,  religion;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serach_now_guset);


        male_btn = (Button) findViewById(R.id.buttonmale);
        femal_btn = (Button) findViewById(R.id.buttonfemale);
        Submit = (Button) findViewById(R.id.Submit);
        context = Serach_NowGuest.this;
        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        spinnerProvince = (Spinner) findViewById(R.id.spinnerProvince);
        spinnerReligion = (Spinner) findViewById(R.id.spinnerReligion);
        malesearch = (LinearLayout) findViewById(R.id.malesearch);
        femalesearch = (LinearLayout) findViewById(R.id.femalesearch);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Serach_NowGuest.this, SearchActivity_multi.class));

                set_SearchList(looking_for,sage,eage,country,state,religion);

            }
        });

        femal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                male_btn.setBackgroundResource(R.drawable.bl);

                femal_btn.setBackgroundResource(R.drawable.wl);

                femal_btn.setTextColor(getResources().getColor(R.color.colorPrimary));

                male_btn.setTextColor(getResources().getColor(R.color.white));

                Toast.makeText(Serach_NowGuest.this, "cheked", Toast.LENGTH_SHORT).show();


            }
        });

        male_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                male_btn.setBackgroundResource(R.drawable.wr);

                femal_btn.setBackgroundResource(R.drawable.br);

                femal_btn.setTextColor(getResources().getColor(R.color.white));

                male_btn.setTextColor(getResources().getColor(R.color.colorPrimary));

                Toast.makeText(Serach_NowGuest.this, "new checked", Toast.LENGTH_SHORT).show();



            }
        });


       final int selectedItem=-1;
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,R.layout.custom_spinner_layout,spinnerCountry_name );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_textview);
        spinnerCountry.setAdapter(spinnerArrayAdapter);



        ArrayAdapter<String> spinnerProvinceArrayAdapter = new ArrayAdapter<String>(this,R.layout.custom_spinner_layout,spinnerProvince_name );
        spinnerProvinceArrayAdapter.setDropDownViewResource(R.layout.spinner_textview);
        spinnerProvince.setAdapter(spinnerProvinceArrayAdapter);



        ArrayAdapter<String> spinnerReligionArrayAdapter = new ArrayAdapter<String>(this,R.layout.custom_spinner_layout,spinnerReligion_name );
        spinnerReligionArrayAdapter.setDropDownViewResource(R.layout.spinner_textview);
        spinnerReligion.setAdapter(spinnerReligionArrayAdapter);



        leftarrow = (ImageView) findViewById(R.id.leftarrow);

        leftarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setmDialog();


            }
        });
    }

    public void setmDialog(){

        LayoutInflater inflater = LayoutInflater.from(context);
        mDialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mDialog.getWindow().setGravity(Gravity.TOP | Gravity.LEFT);
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        lp.dimAmount = 0.75f;
        mDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow();
        mDialog.getWindow().setAttributes(lp);
        View dialoglayout = inflater.inflate(R.layout.rehigtlayout, null);
        mDialog.setContentView(dialoglayout);

        txt_login = (TextView) mDialog.findViewById(R.id.newLogin);

        txt_register = (TextView) mDialog.findViewById(R.id.newRegister);



        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(context, LoginActivity.class));

            }
        });

        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(context, RegisterActivity.class));

            }
        });

        mDialog.show();

    }
    public  void set_SearchList (String looking_for, String sage, String eage, String country, String state, String religion ){

        FileUploadInterface service = RetrofitHandler.getInstance().getApi();

        retrofit2.Call<ResponseBody> call = service.getSearchList(looking_for,sage,eage,country,state,religion);

        final ProgressDialog pDialog = new ProgressDialog(context);

        pDialog.setMessage("Loading...");

        pDialog.show();



        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                pDialog.dismiss();

                try {
                    ResigterResponse=response.body().string();
                    Toast.makeText(context, "Register::"+ResigterResponse, Toast.LENGTH_SHORT).show();

                }
                catch (IOException e) {

                    e.printStackTrace();

                }

                try {
                    JSONObject jsonObject =new JSONObject(ResigterResponse);
                    String message = jsonObject.getString("message");
                    boolean status = jsonObject.getBoolean("response");

                    Log.e("Message"," "+message);

                    Toast.makeText(context, "searchlist::::...."+ResigterResponse, Toast.LENGTH_SHORT).show();

                    if(status)
                    {
                        CommonUtils.savePreferencesBoolean(context, AppConstants.FIRST_TIME_LOGIN, true);

                        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();

                     startActivity(new Intent(Serach_NowGuest.this,SearchActivity_multi.class));
                    }
                    else
                    {
                        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();

                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }



    }


