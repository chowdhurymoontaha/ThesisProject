package com.example.thesissensorapplication;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class myService extends Service {

   /* public myService(){
        super("My_Worker_Thread");
    }*/

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        //  Toast.makeText(this,"Service Started",Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Toast.makeText(this,"Service Stopped",Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
/*
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        synchronized (this){
            int count=0;
            while(count<20){
                try {
                    wait(1500);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }*/
}
