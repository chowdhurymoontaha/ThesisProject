package com.example.thesissensorapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class OptionActivity extends AppCompatActivity implements SensorEventListener
{

    MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(this);
    RadioGroup radioGroup;
    LottieAnimationView animationView;

    private Handler acceleroHandeler = new Handler();
    private Handler gyroscopeHandler = new Handler();
    private Handler magnetoHandeler = new Handler();
    private Handler humidityHandeler = new Handler();
    private Handler lightHandeler = new Handler();
    private Handler pressureHandeler = new Handler();
    private Handler temperatureHandeler = new Handler();
    private Handler gpsHandeler = new Handler();

    private SensorManager sensorManager;
    private Sensor accelerometer, gyroscope, magnetometer, humidity, light, pressure, temperature;

    String str_xValue, str_yValue, str_zValue;
    double acc_x, acc_y, acc_z;
    //String url = "https://moontaha.000webhostapp.com/SensorTest.php";
    String str_gyroXValue, str_gyroYValue, str_gyroZValue;
    double gyro_x, gyro_y, gyro_z;
    //String urlGyroscope = "https://moontaha.000webhostapp.com/GyroscopeTest.php";
    String str_xMagnetoValue, str_yMagnetoValue, str_zMagnetoValue;
    double mag_x, mag_y, mag_z;
    //String url_MagnetoValue = "https://moontaha.000webhostapp.com/MagnetometerSensorTest.php";
    String str_humidity;
    double hum;
    //String urlHumidity = "https://moontaha.000webhostapp.com/HumiditySensorTest.php";
    String str_light;
    double lightData;
    //String urlLight = "https://moontaha.000webhostapp.com/LightSensorTest.php";
    String str_pressure;
    double pressureData;
    // String urlPressure = "https://moontaha.000webhostapp.com/PressureSensorTest.php";
    String str_temperature;
    double temperatureData;
    // String urlTemperature = "https://moontaha.000webhostapp.com/TemperatureSensorTest.php";
    //String urlGPS = "https://moontaha.000webhostapp.com/GPSTest.php";
    String str_Latitude,str_Longitude;
    double Latitude, Longitude;
    String Address = "";

    //Google's API for Location Service.Majority of this app function use this class.
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); //string

        //sensor initialization
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        humidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);


        if (accelerometer != null)
        {
            sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);//alt+sft+ent hardware cast
        }

        if (gyroscope != null)
        {

            sensorManager.registerListener((SensorEventListener) this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);//alt+sft+ent hardware cast
        }

        if (magnetometer != null)
        {

            sensorManager.registerListener((SensorEventListener) this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);//alt+sft+ent hardware cast
        }

        if (humidity != null)
        {

            sensorManager.registerListener((SensorEventListener) this, humidity, SensorManager.SENSOR_DELAY_NORMAL);//alt+sft+ent hardware cast

        }
        if (light != null)
        {

            sensorManager.registerListener((SensorEventListener) this, light, SensorManager.SENSOR_DELAY_NORMAL);//alt+sft+ent hardware cast

        }

        if (pressure != null)
        {

            sensorManager.registerListener((SensorEventListener) this, pressure, SensorManager.SENSOR_DELAY_NORMAL);//alt+sft+ent hardware cast

        }
        if (temperature != null)
        {

            sensorManager.registerListener((SensorEventListener) this, temperature, SensorManager.SENSOR_DELAY_NORMAL);//alt+sft+ent hardware cast

        }

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        animationView = findViewById(R.id.animation);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>()
            {
                @Override
                public void onComplete(@NonNull Task<Location> task)
                {
                    Location location = task.getResult();
                    if (location != null)
                    {
                        try
                        {
                            Geocoder geocoder = new Geocoder(OptionActivity.this, Locale.getDefault());

                            List<Address> addresses = geocoder.getFromLocation(
                                    location.getLatitude(), location.getLongitude(), 1
                            );
                            Latitude = addresses.get(0).getLatitude();
                            Longitude = addresses.get(0).getLongitude();
                            Address = addresses.get(0).getAddressLine(0);
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }

                }
            });

        }
        else
        {
            ActivityCompat.requestPermissions(OptionActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                animationView.setVisibility(View.VISIBLE);
                animationView.playAnimation();
            }
        });

        acceleroRunnable.run();
        gyroscopeRunnable.run();
        magnetoRunnable.run();
        humidityRunnable.run();
        lightRunnable.run();
        pressureRunnable.run();
        temperatureRunnable.run();
        GPSRunnable.run();

    }
    private Runnable temperatureRunnable=new Runnable() {
        @Override
        public void run() {

            Intent intent=new Intent(OptionActivity.this,myService.class);
            startService(intent);
            str_temperature= Double.toString(temperatureData);

            boolean checkTemperatureData=myDatabaseHelper.insertTemperatureValue(str_temperature);
            if(checkTemperatureData==true){
                Toast.makeText(OptionActivity.this,"Temperature value Inserted Successfully",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OptionActivity.this,"Temperature value Insertion Failed",Toast.LENGTH_SHORT).show();
            }

           /* StringRequest request=new StringRequest(Request.Method.POST, urlTemperature, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Toast.makeText(OptionActivity.this,response,Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //  Toast.makeText(OptionActivity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String,String>();

                    params.put("Temperature",str_temperature);

                    return params;
                }
            };


            RequestQueue requestQueue= Volley.newRequestQueue(OptionActivity.this);
            requestQueue.add(request);*/


            temperatureHandeler.postDelayed(temperatureRunnable,60000);

        }
    };


    private Runnable acceleroRunnable=new Runnable() {
        @Override
        public void run()
        {

            Intent intent = new Intent(OptionActivity.this, myService.class);
            startService(intent);
            str_xValue = Double.toString(acc_x);
            str_yValue = Double.toString(acc_y);
            str_zValue = Double.toString(acc_z);

            boolean checkinsertAccelerodata=myDatabaseHelper.insertAccelerometerValue(str_xValue,str_yValue,str_zValue);
            if(checkinsertAccelerodata==true){
                Toast.makeText(OptionActivity.this,"Inserted Successfully",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OptionActivity.this,"Insertion Failed",Toast.LENGTH_SHORT).show();
            }


           /* StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response)
                {
                    //   Toast.makeText(OptionActivity.this, response, Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    //  Toast.makeText(OptionActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                }
            }
            )
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("xAccelerValue", str_xValue);
                    params.put("yAccelerValue", str_yValue);
                    params.put("zAccelerValue", str_zValue);

                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(OptionActivity.this);
            requestQueue.add(request);*/


            acceleroHandeler.postDelayed(acceleroRunnable,60000);

        }
    };
    private Runnable gyroscopeRunnable=new Runnable() {
        @Override
        public void run() {

            Intent intent=new Intent(OptionActivity.this,myService.class);
            startService(intent);
            str_gyroXValue= Double.toString(gyro_x);
            str_gyroYValue= Double.toString(gyro_y);
            str_gyroZValue= Double.toString(gyro_z);
            boolean checkinsertGyroscopedata=myDatabaseHelper.insertGyroscopeValue(str_gyroXValue,str_gyroYValue,str_gyroZValue);
            if(checkinsertGyroscopedata==true){
                Toast.makeText(OptionActivity.this,"Gyroscope data Inserted Successfully",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OptionActivity.this,"Gyroscope data Insertion Failed",Toast.LENGTH_SHORT).show();
            }


           /* StringRequest request=new StringRequest(Request.Method.POST, urlGyroscope, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //  Toast.makeText(OptionActivity.this,response,Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Toast.makeText(OptionActivity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();

                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String,String>();

                    params.put("xGyroscope",str_gyroXValue);
                    params.put("yGyroscope",str_gyroYValue);
                    params.put("zGyroscope",str_gyroZValue);

                    return params;
                }
            };


            RequestQueue requestQueue= Volley.newRequestQueue(OptionActivity.this);
            requestQueue.add(request);*/


            gyroscopeHandler.postDelayed(gyroscopeRunnable,60000);

        }
    };
    private Runnable magnetoRunnable=new Runnable() {
        @Override
        public void run() {

            Intent intent=new Intent(OptionActivity.this,myService.class);
            startService(intent);
            str_xMagnetoValue= Double.toString(mag_x);
            str_yMagnetoValue= Double.toString(mag_y);
            str_zMagnetoValue= Double.toString(mag_z);

            boolean checkinsertMagnetodata=myDatabaseHelper.insertMagnetometerValue(str_xMagnetoValue,str_yMagnetoValue,str_zMagnetoValue);
            if(checkinsertMagnetodata==true){
                Toast.makeText(OptionActivity.this,"Inserted Magnetometer Data Successfully",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OptionActivity.this,"Magnetometer Data Insertion Failed",Toast.LENGTH_SHORT).show();
            }



            /*StringRequest request=new StringRequest(Request.Method.POST, url_MagnetoValue, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Toast.makeText(OptionActivity.this,response,Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Toast.makeText(OptionActivity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();

                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String,String>();

                    params.put("xMagnetovalue",str_xMagnetoValue);
                    params.put("yMagnetovalue",str_yMagnetoValue);
                    params.put("zMagnetovalue",str_zMagnetoValue);

                    return params;
                }
            };


            RequestQueue requestQueue= Volley.newRequestQueue(OptionActivity.this);
            requestQueue.add(request);*/

            magnetoHandeler.postDelayed(magnetoRunnable,60000);

        }
    };
    private Runnable humidityRunnable=new Runnable() {
        @Override
        public void run() {

            Intent intent=new Intent(OptionActivity.this,myService.class);
            startService(intent);
            str_humidity= Double.toString(hum);

            boolean checkinsertHumiditydata=myDatabaseHelper.insertHumidityValue(str_humidity);
            if(checkinsertHumiditydata==true){
                Toast.makeText(OptionActivity.this,"Humidity data Inserted Successfully",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OptionActivity.this,"Humidity data Insertion Failed",Toast.LENGTH_SHORT).show();
            }


            /*StringRequest request=new StringRequest(Request.Method.POST, urlHumidity, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Toast.makeText(OptionActivity.this,response,Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Toast.makeText(OptionActivity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();

                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String,String>();

                    params.put("Humidity",str_humidity);

                    return params;
                }
            };

            RequestQueue requestQueue= Volley.newRequestQueue(OptionActivity.this);
            requestQueue.add(request);*/


            humidityHandeler.postDelayed(humidityRunnable,60000);

        }
    };
    private Runnable lightRunnable=new Runnable() {
        @Override
        public void run() {

            Intent intent=new Intent(OptionActivity.this,myService.class);
            startService(intent);
            str_light= Double.toString(lightData);

            boolean checkLightData=myDatabaseHelper.insertLightValue(str_light);
            if(checkLightData==true){
                Toast.makeText(OptionActivity.this,"Light value Inserted Successfully",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OptionActivity.this,"Light value Insertion Failed",Toast.LENGTH_SHORT).show();
            }

           /* StringRequest request=new StringRequest(Request.Method.POST, urlLight, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //  Toast.makeText(OptionActivity.this,response,Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //  Toast.makeText(OptionActivity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();

                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String,String>();

                    params.put("lightIntensity",str_light);

                    return params;
                }
            };


            RequestQueue requestQueue= Volley.newRequestQueue(OptionActivity.this);
            requestQueue.add(request);*/


            lightHandeler.postDelayed(lightRunnable,60000);

        }
    };
    private Runnable pressureRunnable=new Runnable() {
        @Override
        public void run() {

            Intent intent=new Intent(OptionActivity.this,myService.class);
            startService(intent);
            str_pressure= Double.toString(pressureData);

            boolean checkPressureData=myDatabaseHelper.insertPressureValue(str_pressure);
            if(checkPressureData==true){
                Toast.makeText(OptionActivity.this,"Pressure value Inserted Successfully",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OptionActivity.this,"Pressure value Insertion Failed",Toast.LENGTH_SHORT).show();
            }

            /*StringRequest request=new StringRequest(Request.Method.POST, urlPressure, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //  Toast.makeText(OptionActivity.this,response,Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //  Toast.makeText(OptionActivity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();

                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String,String>();

                    params.put("Pressure",str_pressure);

                    return params;
                }
            };

            RequestQueue requestQueue= Volley.newRequestQueue(OptionActivity.this);
            requestQueue.add(request);*/


            pressureHandeler.postDelayed(pressureRunnable,60000);

        }
    };
    private Runnable GPSRunnable=new Runnable() {
        @Override
        public void run() {

            Intent intent=new Intent(OptionActivity.this,myService.class);
            startService(intent);

            str_Latitude=Double.toString(Latitude);
            str_Longitude=Double.toString(Longitude);


            boolean checkinsertGPSdata=myDatabaseHelper.insertGPSValue(str_Longitude,str_Latitude,Address);
            if(checkinsertGPSdata==true){
                Toast.makeText(OptionActivity.this,"GPS dataInserted Successfully",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(OptionActivity.this,"GPS data Insertion Failed",Toast.LENGTH_SHORT).show();
            }

            /*StringRequest request=new StringRequest(Request.Method.POST, urlGPS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(OptionActivity.this,response,Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Toast.makeText(OptionActivity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();

                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<String,String>();
                    Log.d("OptionActitvity","long"+Longitude+"lat :"+Latitude+"Address :"+Address);

                    params.put("Longitude",Double.toString(Longitude));
                    params.put("Latitude",Double.toString(Latitude));
                    params.put("Address",Address);

                    return params;
                }
            };


            RequestQueue requestQueue= Volley.newRequestQueue(OptionActivity.this);
            requestQueue.add(request);*/
            gpsHandeler.postDelayed(GPSRunnable,60000);

        }
    };


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor sensor=sensorEvent.sensor;

        if(sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            acc_x = sensorEvent.values[0];
            acc_y = sensorEvent.values[1];
            acc_z = sensorEvent.values[2];
        }

        if(sensor.getType()==Sensor.TYPE_GYROSCOPE){

            gyro_x = sensorEvent.values[0];
            gyro_y = sensorEvent.values[1];
            gyro_z = sensorEvent.values[2];
        }

        if(sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){

            mag_x = sensorEvent.values[0];
            mag_y = sensorEvent.values[1];
            mag_z = sensorEvent.values[2];
        }
        if(sensor.getType()==Sensor.TYPE_RELATIVE_HUMIDITY){
            hum = sensorEvent.values[0];
        }

        if(sensor.getType()==Sensor.TYPE_LIGHT){
            lightData = sensorEvent.values[0];
        }
        if(sensor.getType()==Sensor.TYPE_PRESSURE){
            pressureData = sensorEvent.values[0];
        }
        if(sensor.getType()==Sensor.TYPE_AMBIENT_TEMPERATURE){
            temperatureData = sensorEvent.values[0];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}