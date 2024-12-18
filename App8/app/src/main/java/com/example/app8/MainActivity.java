package com.example.app8;

import android.os.AsyncTask;
import android.os.Bundle;
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