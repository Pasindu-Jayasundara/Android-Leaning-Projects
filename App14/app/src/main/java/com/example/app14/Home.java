package com.example.app14;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app14.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent i = getIntent();
        String userJson = i.getStringExtra("userJson");

        Gson gson = new Gson();
        User user = gson.fromJson(userJson, User.class);

        TextView nameView = findViewById(R.id.textViewName);
        TextView mobileView = findViewById(R.id.textViewMobile);
        TextView cityView = findViewById(R.id.textViewCity);
        TextView passwordView = findViewById(R.id.textViewPassword);

        nameView.setText(user.getName());
        mobileView.setText(user.getMobile());
        cityView.setText(user.getCity());
        passwordView.setText(user.getPassword());


    }
}