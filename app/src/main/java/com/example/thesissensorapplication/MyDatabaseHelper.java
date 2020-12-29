package com.example.thesissensorapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String ACCELEROMETER_TABLE = "AccelerometerTable";
    public static final String COLUMN_ACC_ID = "AccID";
    public static final String COLUMN_X_ACCELEROMETER_VALUE = "xAccelerometerValue";
    public static final String COLUMN_Y_ACCELEROMETER_VALUE = "yAccelerometerValue";
    public static final String COLUMN_Z_ACCELEROMETER_VALUE = "zAccelerometerValue";

    public static final String GYROSCOPE_TABLE = "GyroscopeTable";
    public static final String COLUMN_GYROSCOPE_ID = "GyroscopeID";
   // public static final String COLUMN_TIMESTAMP_GYROSCOPE = "GyroscopeTimeStamp";
    public static final String COLUMN_X_GYROSCOPE_VALUE = "xGyroscopeValue";
    public static final String COLUMN_Y_GYROSCOPE_VALUE = "yGyroscopeValue";
    public static final String COLUMN_Z_GYROSCOPE_VALUE = "zGyroscopeValue";


    public static final String MAGNETOMETER_TABLE = "MagnetometerTable";
    public static final String COLUMN_MAGNETO_ID = "MagnetoID";
    public static final String COLUMN_X_MAGNETOMETER_VALUE = "xMagnetometerValue";
    public static final String COLUMN_Y_MAGNETOMETER_VALUE = "yMagnetometerValue";
    public static final String COLUMN_Z_MAGNETOMETER_VALUE = "zMagnetometerValue";

    public static final String LIGHT_TABLE = "LightTable";
    public static final String COLUMN_LIGHT_ID = "LightID";
    public static final String COLUMN_LIGHT_VALUE = "LightValue";

    public static final String TEMPERATURE_TABLE = "TemperatureTable";
    public static final String COLUMN_TEMPERATURE_ID = "TemperatureID";
    //public static final String COLUMN_TEMPERATURE_TIMESTAMP = "TemperatureTimeStamp";
    public static final String COLUMN_TEMPERATURE_VALUE = "TemperatureValue";

    public static final String PRESSURE_TABLE = "PressureTable";
    public static final String COLUMN_PRESSURE_ID = "PressureID";
    //public static final String COLUMN_TPRESSURE_TIMESTAMP = "PressureTimeStamp";
    public static final String COLUMN_PRESSURE_VALUE = "PressureValue";

    public static final String HUMIDITY_TABLE = "HumidityTable";
    public static final String COLUMN_HUMIDITY_ID = "HumidityID";
    //public static final String COLUMN_HUMIDITY_TIMESTAMP = "HumidityTimeStamp";
    public static final String COLUMN_HUMIDITY_VALUE = "HumidityValue";

    public static final String GPS_TABLE = "GPSTable";
    public static final String COLUMN_GPS_ID = "GPSID";
    //public static final String COLUMN_GPS_TIMESTAMP = "GPSTimeStamp";
    public static final String COLUMN_GPS_LONGITUDE_VALUE = "longitudeGPSValue";
    public static final String COLUMN_GPS_LATITUDE_VALUE = "latitudeGPSValue";
    public static final String COLUMN_GPS_ADDRESS_VALUE = "addressGPS";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, "MultipleSensorValues.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Accelerometer
         String createTableStatement= "CREATE TABLE " + ACCELEROMETER_TABLE+ "(" + COLUMN_ACC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUMN_X_ACCELEROMETER_VALUE + " TEXT, " + COLUMN_Y_ACCELEROMETER_VALUE + " TEXT, " + COLUMN_Z_ACCELEROMETER_VALUE + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);

        //Gyroscope
        String GyroscopeCreateTableStatement= "CREATE TABLE " + GYROSCOPE_TABLE + " (" + COLUMN_GYROSCOPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUMN_X_GYROSCOPE_VALUE + " TEXT, " + COLUMN_Y_GYROSCOPE_VALUE + " TEXT, " + COLUMN_Z_GYROSCOPE_VALUE + " TEXT)";
        sqLiteDatabase.execSQL(GyroscopeCreateTableStatement);


        //Magnetometer
        String MagnetometerCreateTableStatement= "CREATE TABLE " + MAGNETOMETER_TABLE + " (" + COLUMN_MAGNETO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUMN_X_MAGNETOMETER_VALUE + " TEXT, " + COLUMN_Y_MAGNETOMETER_VALUE + " TEXT, " + COLUMN_Z_MAGNETOMETER_VALUE + " TEXT)";
        sqLiteDatabase.execSQL(MagnetometerCreateTableStatement);

        //light
        String LightCreateTableStatement= "CREATE TABLE " + LIGHT_TABLE + "(" + COLUMN_LIGHT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUMN_LIGHT_VALUE + " TEXT)";
        sqLiteDatabase.execSQL(LightCreateTableStatement);

        //Humidity
        String HumidityCreateTableStatement= "CREATE TABLE " + HUMIDITY_TABLE + "(" + COLUMN_HUMIDITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUMN_HUMIDITY_VALUE + " TEXT)";
        sqLiteDatabase.execSQL(HumidityCreateTableStatement);


        //Temperature
        String TemperatureCreateTableStatement= "CREATE TABLE " + TEMPERATURE_TABLE + "(" + COLUMN_TEMPERATURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"  + COLUMN_TEMPERATURE_VALUE + " TEXT)";
        sqLiteDatabase.execSQL(TemperatureCreateTableStatement);

        //Pressure
        String PressureCreateTableStatement= "CREATE TABLE " + PRESSURE_TABLE + "(" + COLUMN_PRESSURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"  + COLUMN_PRESSURE_VALUE + " TEXT)";
        sqLiteDatabase.execSQL(PressureCreateTableStatement);


        //GPS

        String GPScreateTableStatement= "CREATE TABLE " + GPS_TABLE+ "(" + COLUMN_GPS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUMN_GPS_LONGITUDE_VALUE + " TEXT, " + COLUMN_GPS_LATITUDE_VALUE + " TEXT, " + COLUMN_GPS_ADDRESS_VALUE + " TEXT)";
        sqLiteDatabase.execSQL(GPScreateTableStatement);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String dropTableStatement="DROP TABLE IF EXISTS "+ACCELEROMETER_TABLE;
        sqLiteDatabase.execSQL(dropTableStatement);

        String GyroscopeDropTableStatement="DROP TABLE IF EXISTS "+GYROSCOPE_TABLE;
        sqLiteDatabase.execSQL(GyroscopeDropTableStatement);


        String MagnetoDropTableStatement="DROP TABLE IF EXISTS "+MAGNETOMETER_TABLE;
        sqLiteDatabase.execSQL(MagnetoDropTableStatement);

        String LightDropTableStatement="DROP  TABLE IF EXISTS "+LIGHT_TABLE;
        sqLiteDatabase.execSQL(LightDropTableStatement);

        String HumidityDropTableStatement="DROP  TABLE IF EXISTS "+HUMIDITY_TABLE;
        sqLiteDatabase.execSQL(HumidityDropTableStatement);

        String TemperatureDropTableStatement="DROP  TABLE IF EXISTS "+TEMPERATURE_TABLE;
        sqLiteDatabase.execSQL(TemperatureDropTableStatement);

        String PressureDropTableStatement="DROP  TABLE IF EXISTS "+PRESSURE_TABLE;
        sqLiteDatabase.execSQL(PressureDropTableStatement);

        String GPSDropTableStatement="DROP  TABLE IF EXISTS "+GPS_TABLE;
        sqLiteDatabase.execSQL(GPSDropTableStatement);

    }

    public boolean insertAccelerometerValue(String xAccelerometerValue,String yAccelerometerValue,String zAccelerometerValue){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_X_ACCELEROMETER_VALUE,xAccelerometerValue);
        cv.put(COLUMN_Y_ACCELEROMETER_VALUE,yAccelerometerValue);
        cv.put(COLUMN_Z_ACCELEROMETER_VALUE,zAccelerometerValue);

        long l = sqLiteDatabase.insert(ACCELEROMETER_TABLE, null, cv);
        if(l==-1){
            return false;
        }
        else {
            return true;
        }

    }

    public boolean insertGyroscopeValue(String xGyroscopeValue,String yGyroscopeValue,String zGyroscopeValue){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        //cv.put(COLUMN_TIMESTAMP_GYROSCOPE,GyroscopeTimeStamp);
        cv.put(COLUMN_X_GYROSCOPE_VALUE,xGyroscopeValue);
        cv.put(COLUMN_Y_GYROSCOPE_VALUE,yGyroscopeValue);
        cv.put(COLUMN_Z_GYROSCOPE_VALUE,zGyroscopeValue);

        long l= sqLiteDatabase.insert(GYROSCOPE_TABLE, null, cv);
        if(l==-1){
            return false;
        }
        else {
            return true;
        }

    }

    public boolean insertMagnetometerValue(String xMagnetometerValue,String yMagnetometerValue,String zMagnetometerValue){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_X_MAGNETOMETER_VALUE,xMagnetometerValue);
        cv.put(COLUMN_Y_MAGNETOMETER_VALUE,yMagnetometerValue);
        cv.put(COLUMN_Z_MAGNETOMETER_VALUE,zMagnetometerValue);

        long l= sqLiteDatabase.insert(MAGNETOMETER_TABLE, null, cv);
        if(l==-1){
            return false;
        }
        else {
            return true;
        }

    }

    public boolean insertLightValue(String LightValue ){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_LIGHT_VALUE,LightValue);
        long l=sqLiteDatabase.insert(LIGHT_TABLE,null,contentValues);
        if(l==-1){
            return false;
        }
        else{
            return  true;
        }
    }

    public boolean insertHumidityValue(String HumidityValue ){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_HUMIDITY_VALUE,HumidityValue);
        long l=sqLiteDatabase.insert(HUMIDITY_TABLE,null,contentValues);
        if(l==-1){
            return false;
        }
        else{
            return  true;
        }
    }

    public boolean insertTemperatureValue(String TemperatureValue){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        //contentValues.put(COLUMN_TEMPERATURE_TIMESTAMP,TemperatureTimeStamp);
        contentValues.put(COLUMN_TEMPERATURE_VALUE,TemperatureValue);

        long l=sqLiteDatabase.insert(TEMPERATURE_TABLE,null,contentValues);
        if(l==-1){
            return false;
        }
        else{
            return  true;
        }
    }

    public boolean insertPressureValue(String PressureValue){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        //contentValues.put(COLUMN_TEMPERATURE_TIMESTAMP,TemperatureTimeStamp);
        contentValues.put(COLUMN_PRESSURE_VALUE,PressureValue);

        long l=sqLiteDatabase.insert(PRESSURE_TABLE,null,contentValues);
        if(l==-1){
            return false;
        }
        else{
            return  true;
        }
    }

    public boolean insertGPSValue(String longitudeGPSValue,String latitudeGPSValue,String addressGPS){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_GPS_LONGITUDE_VALUE,longitudeGPSValue);
        cv.put(COLUMN_GPS_LATITUDE_VALUE,latitudeGPSValue);
        cv.put(COLUMN_GPS_ADDRESS_VALUE,addressGPS);

        long l = sqLiteDatabase.insert(GPS_TABLE, null, cv);
        if(l==-1){
            return false;
        }
        else {
            return true;
        }

    }


}
