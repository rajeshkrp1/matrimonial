package com.example.admin.metromonial.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.metromonial.NetworkConnection.FileUploadInterface;
import com.example.admin.metromonial.NetworkConnection.RetrofitHandler;
import com.example.admin.metromonial.R;
import com.example.admin.metromonial.Utils.AppConstants;
import com.example.admin.metromonial.Utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 11/23/2017.
 */


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button Submit_btn;
    String email, password , mobile,name;
    Context context;
    private String ResigterResponse="";
    SharedPreferences.Editor editor;
    EditText fullname_ET;
    EditText email_ET;
    EditText password_ET,mobile_ET;
    TextView tv_fullname,tv_email,tv_password,tv_mobile;
    String USER_ID="",mobileNo="";
    String text="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_activity);

        context= RegisterActivity.this;

        fullname_ET = (EditText)findViewById(R.id.fullname);

        email_ET = (EditText)findViewById(R.id.eamilreg);

        password_ET = (EditText)findViewById(R.id.password_et);

        mobile_ET = (EditText)findViewById(R.id.ed_mobile);

        Submit_btn = (Button)findViewById(R.id.submitnmain);

         tv_fullname=(TextView)findViewById(R.id.tvfullname);
         tv_email=(TextView)findViewById(R.id.tvemil);
         tv_password= (TextView) findViewById(R.id.tvpassword);
         tv_mobile= (TextView) findViewById(R.id.tvmobileno);

        String text = mobile_ET.getText().toString();
        fullname_ET.setOnClickListener(this);
        email_ET.setOnClickListener(this);
        password_ET.setOnClickListener(this);
        mobile_ET.setOnClickListener(this);
       /* fullname_ET.setHint(fullname_ET.getHint()+" *");
        password_ET.setHint(password_ET.getHint()+" *");
        email_ET.setHint(email_ET.getHint()+" *");
        mobile_ET.setHint(mobile_ET.getHint()+" *");
*/


        Submit_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                InputMethodManager inputManager = (InputMethodManager)

                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                Register_validate();

            }
        }
        )
        ;
    }

    private   void Register_TAsk(String email, String password,String mobile, String name){

        FileUploadInterface service = RetrofitHandler.getInstance().getApi();

        retrofit2.Call<ResponseBody> call = service.getRegister(email,password,mobile,name);

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

                    String otpget = jsonObject.getString("otp");


                    boolean status = jsonObject.getBoolean("response");

                    Log.e("Message"," "+message);


                    Toast.makeText(context, "Registernew::"+ResigterResponse, Toast.LENGTH_SHORT).show();


                    if(status)
                    {
                        CommonUtils.savePreferencesBoolean(context, AppConstants.FIRST_TIME_LOGIN, true);

                        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context,OTP_Activity.class);
                        intent.putExtra("otp",otpget);
                          startActivity(intent);
                    }


                   /* else if (status){
                        startActivity(new Intent(c));
                    }
*/


                    else
                    {
                        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
                       /* Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context,OTP_Activity.class));

                        Intent i = new Intent(context,OTP_Activity.class);
                        i.putExtra("otp",otpget);
                        startActivity(i);*/


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
    public  void  Register_validate(){

        String email = email_ET.getText().toString();
        String password = password_ET.getText().toString();
        String mobile = mobile_ET.getText().toString();
        String name = fullname_ET.getText().toString();

        if (TextUtils.isEmpty(fullname_ET.getText().toString())) {


            CommonUtils.snackBar( "Please Enter  Your Fullname .",fullname_ET);
        }
        else if (TextUtils.isEmpty(email_ET.getText().toString())) {

            CommonUtils.snackBar( "Please Enter  Your Email .",fullname_ET);
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            CommonUtils.snackBar( "Please Enter Your Valid Email .",email_ET);
        }
        else if(TextUtils.isEmpty(mobile_ET.getText().toString())){

            CommonUtils.snackBar("Enter Your Mobile Number",fullname_ET);
        }
        else if (TextUtils.isEmpty(password)) {

            CommonUtils.snackBar( "Please Enter Your Password .",fullname_ET);
        }
        else {

          //  CommonUtils.snackBar( "Register  .",fullname_ET);
            Intent intent=new Intent(this,CreateProfileActivity.class);
            startActivity(intent);

            //Register_TAsk(email,password,mobile,name);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fullname:
                if(fullname_ET.getText().length()>0){
                    tv_fullname.setVisibility(View.GONE);
                }else tv_fullname.setVisibility(View.VISIBLE);

                break;
            case R.id.eamilreg:
                tv_email.setVisibility(View.GONE);
                break;
            case R.id.ed_mobile:
                tv_mobile.setVisibility(View.GONE);
                break;
            case R.id.password_et:
                tv_password.setVisibility(View.GONE);
                break;
        }
    }
}



