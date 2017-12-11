package com.example.admin.metromonial.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    Button loginmain, registerBtn;
    EditText email_ET, pass_ET;
    Context context;
    private String loginResponse="";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        email_ET = (EditText)findViewById(R.id.emailusername);

        pass_ET = (EditText)findViewById(R.id.passwordlogin);

        loginmain = (Button)findViewById(R.id.loginmain);

        registerBtn= (Button) findViewById(R.id.button_register);


        registerBtn.setOnClickListener(this);



        context = LoginActivity.this;

        loginmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                InputMethodManager inputManager = (InputMethodManager)

                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                Login_validate();

            }
        });
    }

    public void loginTask(String email,String password)
    {
        FileUploadInterface service = RetrofitHandler.getInstance().getApi();

        retrofit2.Call<ResponseBody> call = service.getLogin(email,password);

        final ProgressDialog pDialog = new ProgressDialog(context);

        pDialog.setMessage("Loading...");

        pDialog.show();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                pDialog.dismiss();

                try {
                 loginResponse=response.body().string();
                    Toast.makeText(LoginActivity.this, "login::"+loginResponse, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject jsonObject =new JSONObject(loginResponse);
                    boolean status = jsonObject.getBoolean("response");
                    if(status)
                    {
                        startActivity(new Intent(context,MeilleureCouple.class));
                        CommonUtils.savePreferencesBoolean(context, AppConstants.FIRST_TIME_LOGIN, true);
                        Toast.makeText(LoginActivity.this,"Login Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(context,"Invalid Credentials", Toast.LENGTH_SHORT).show();
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

    public  void  Login_validate(){

        String email = email_ET.getText().toString();

        String password = pass_ET.getText().toString();

        if (TextUtils.isEmpty(email_ET.getText().toString())) {

            CommonUtils.snackBar( "Please Enter Your Email .",email_ET);

        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            CommonUtils.snackBar( "Please Enter Your Valid Email .",email_ET);

        }

        else if (TextUtils.isEmpty(password)) {
            CommonUtils.snackBar( "Please Enter Your Password .",email_ET);
        }

        else {

            Intent intent=new Intent(this,MeilleureCouple.class);
            startActivity(intent);

            //loginTask(email,password);

        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_register:
                Intent intent=new Intent(context,RegisterActivity.class);
                startActivity(intent);
        }
    }
}


