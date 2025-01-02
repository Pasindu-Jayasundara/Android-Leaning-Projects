package com.example.app16;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

                Log.i("App16Log",String.valueOf(sensorList.size()));

                for (Sensor sensor : sensorList) {
                    Log.i("App16Log",sensor.getName());
                }

            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                if(sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)!=null){
                    Log.i("App16Log","Magnetic Field Sensor is available");
                }else{
                    Log.i("App16Log","Magnetic Field Sensor is not available");
                }

            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)==null){
                    return;
                }
                Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

                SensorEventListener sensorEventListener=  new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent sensorEvent) {

                        float values[] = sensorEvent.values;
                        Log.i("App16Log","Accelerometer Sensor Value X : "+String.valueOf(values[0]));
                        Log.i("App16Log","Accelerometer Sensor Value Y : "+String.valueOf(values[1]));
                        Log.i("App16Log","Accelerometer Sensor Value Z : "+String.valueOf(values[2]));
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int i) {

                    }
                };

                sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);

            }
        });
    }
}