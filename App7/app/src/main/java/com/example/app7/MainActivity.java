package com.example.app7;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
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

                Thread t = new Thread(){
                    @Override
                    public void run() {

                        for (int i = 0; i <= 10; i++) {
                            final int x = i;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TextView tv = findViewById(R.id.textView1);
                                    tv.setText(String.valueOf(x));
                                }
                            });

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                };
                t.start();

            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsyncTask asyncTask = new AsyncTask() {

                    @Override
                    protected void onPreExecute() {
                        Log.i("AsyncTask", "onPreExecute");
                    }

                    @Override
                    protected Object doInBackground(Object[] objects) {

                        for (int i = 0; i <= 10; i++) {
                            try {
                                publishProgress(i);
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        return null;
                    }

                    @Override
                    protected void onProgressUpdate(Object[] values) {

                        int x = (int) values[0];

                        TextView tv = findViewById(R.id.textView1);
                        tv.setText(String.valueOf(x));

                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        Log.i("AsyncTask", "onPostExecute");
                    }

                };
                asyncTask.execute("");

            }
        });
    }
}