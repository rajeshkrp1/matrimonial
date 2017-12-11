package com.example.admin.metromonial.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.example.admin.metromonial.R;

/**
 * Created by Admin on 11/27/2017.
 */

public class OTP_Activity extends AppCompatActivity {

    TextView textnumber , donetext;
    PinEntryEditText otpText;
    Context context;
    String text = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_activity);
        textnumber = (TextView)findViewById(R.id.textnumber);
        donetext = (TextView)findViewById(R.id.donetext);

        context = OTP_Activity.this;

       /* Intent intent = getIntent();
        String str = intent.getStringExtra("otp");
        otpText.setText(str);*/

       otpText= (PinEntryEditText)findViewById(R.id.txt_pin_entry) ;

       // otpText.setText(getIntent().getStringExtra("otp"));

  /*
        String text = getIntent().getStringExtra("otp");
        otpText.setText(text);
        */

        String s = getIntent().getStringExtra("otp");
        otpText.setText(s);

        Toast.makeText(context, "checkotplist:::::: "+ s, Toast.LENGTH_SHORT).show();

        donetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,CreateProfileActivity.class));
            }
        });
        textnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }
}
