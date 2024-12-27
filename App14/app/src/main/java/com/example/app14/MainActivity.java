package com.example.app14;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

        // "http://127.0.0.1/AndroidBackend/GetRequestTest"
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("app 14 : request btn", "sending http get request");

                String url = "http://127.0.0.1/AndroidBackend/GetRequestTest";

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        OkHttpClient okHttpClient = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url(url)
                                .build();

                        try {
                            Response response = okHttpClient.newCall(request).execute();
                            String responseText = response.body().string();

                            Log.i("app 14 : responseText", responseText);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();

            }
        });
    }
}