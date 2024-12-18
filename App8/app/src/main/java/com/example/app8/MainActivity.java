package com.example.app8;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

                new AsyncTask<Integer,Integer,String>() {

                    @Override
                    protected void onPreExecute() {
                        ((TextView) findViewById(R.id.textView1)).setText("Start");
                    }

                    @Override
                    protected String doInBackground(Integer... integers) {

                        if(integers.length < 4){
                            return "Insufficient Parameter Cunt";
                        }

                        if(integers[1] > integers[0]){
                            return "Invalid Range";
                        }

                        try {
                            Thread.sleep(integers[3]);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        for (int i = integers[0]; i <= integers[1]; i--) {
                            publishProgress(i);
                            try {
                                Thread.sleep(integers[2]);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        try {
                            Thread.sleep(integers[3]);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        return "End";
                    }

                    @Override
                    protected void onProgressUpdate(Integer... values) {
                        ((TextView) findViewById(R.id.textView1)).setText(String.valueOf(values[0]));
                    }

                    @Override
                    protected void onPostExecute(String string) {
                        ((TextView) findViewById(R.id.textView1)).setText(string);
                    }

                }.execute(10,0,1000,5000);

            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String permissionArray[] = new String[2];
                int index = 0;

                if(checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    Log.i("App 8","Call Phone Permission Granted");
                }else {
                    Log.i("App 8", "Call Phone Permission Denied");
                    permissionArray[index] = Manifest.permission.CALL_PHONE;
                    index++;
                }

                if(checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
                    Log.i("App 8","Read Contacts Permission Granted");
                }else{
                    Log.i("App 8","Read Contacts Permission Denied");
                    permissionArray[index] = Manifest.permission.READ_CONTACTS;
                }
                requestPermissions(permissionArray,100);

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {

            for (int i = 0; i < permissions.length; i++) {

                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        Log.i("App 8", "Call Phone Permission Granted");
                    } else {
                        Log.i("App 8", "Call Phone Permission Denied");
                    }
                } else if (permission.equals(Manifest.permission.READ_CONTACTS)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        Log.i("App 8", "Read Contacts Permission Granted");
                    } else {
                        Log.i("App 8", "Read Contacts Permission Denied");
                    }
                }
            }
        }
    }
}

class A extends AsyncTask<Boolean, Integer, String>{

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(Boolean... booleans) {
        return "Done";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
    }

    @Override
    protected void onPostExecute(String string) {
    }


}