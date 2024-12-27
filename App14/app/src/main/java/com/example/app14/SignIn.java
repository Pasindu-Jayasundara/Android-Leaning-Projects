package com.example.app14;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://127.0.0.1/AndroidBackend/SignIn";
                EditText mobileEditText = findViewById(R.id.editTextMobile);
                EditText passwordEditText = findViewById(R.id.editTextPassword);

                String mobile = mobileEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Gson gson = new Gson();

                        JsonObject jo = new JsonObject();
                        jo.addProperty("mobile", mobile);
                        jo.addProperty("password", password);

                        OkHttpClient okHttpClient = new OkHttpClient();
                        RequestBody requestBody = RequestBody.create(gson.toJson(jo), MediaType.get("application/json"));

                        Request request = new Request.Builder()
                                .url(url)
                                .post(requestBody)
                                .build();

                        try {
                            Response response = okHttpClient.newCall(request).execute();
                            String responseText = response.body().string();

                            JsonObject jsonObject = gson.fromJson(responseText, JsonObject.class);
                            if(jsonObject.get("message").getAsString().equals("Success")){

                                Intent i = new Intent(SignIn.this, Home.class);
                                i.putExtra("name", jsonObject.get("name").getAsString());
                                i.putExtra("mobile", jsonObject.get("mobile").getAsString());
                                i.putExtra("password", jsonObject.get("password").getAsString());
                                i.putExtra("city", jsonObject.get("city").getAsString());
                                startActivity(i);

                            }else{
                                Toast.makeText(SignIn.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }).start();

            }
        });
    }
}