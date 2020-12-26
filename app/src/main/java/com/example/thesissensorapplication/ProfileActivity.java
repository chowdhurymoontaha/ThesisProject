package com.example.thesissensorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
  ProgressBar progressBar;
   int progress;
   EditText userNameEditText,PhoneNumberEdittext;
     Button saveButton;
     SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);
        progressBar=(ProgressBar) findViewById(R.id.progressBarId);
        userNameEditText=(EditText)findViewById(R.id.usernameEdittextId);
        PhoneNumberEdittext=(EditText)findViewById(R.id.PhoneNumberEdittextId);
        saveButton=(Button)findViewById(R.id.saveButtonId);
        sessionManager=new SessionManager(getApplicationContext());
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username=userNameEditText.getText().toString().trim();
                String PhoneNumber=PhoneNumberEdittext.getText().toString().trim();
                if(PhoneNumber.equals("") || Username.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Data",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    sessionManager.setLogin(true);
                    sessionManager.setUsername(Username);
                    Toast.makeText(getApplicationContext()," Data is stored successfully",Toast.LENGTH_SHORT).show();
                    Thread thread=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            doWork();

                        }
                    });
                    thread.start();
                    startActivity(new Intent(getApplicationContext(),OptionActivity.class));
                    finish();
                }



            }
        });

     if(sessionManager.getLogin()){
         startActivity(new Intent(getApplicationContext(),OptionActivity.class));

     }


    }
    public void doWork(){

        for(progress=10;progress<=100;progress=progress+10)
        {
            try {
                Thread.sleep(10);
                progressBar.setProgress(progress);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }

    }
   /* public void startApp(){
        SharedPreferences sharedPreferences = getSharedPreferences("ProfileDetails", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("usernameKey") && sharedPreferences.contains("PhoneNumberkey") )
        {
            String Username=sharedPreferences.getString("usernameKey","Data Not Found");
            String PhoneNumber=sharedPreferences.getString("PhoneNumberkey","Data Not Found");
            Intent intent =new Intent(ProfileActivity.this,OptionActivity.class);
            intent.putExtra("key",Username);
            intent.putExtra("key1",PhoneNumber);
            setResult(1,intent);
            finish();
        }


    }*/
}