package com.example.app6;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

public class UserRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        findViewById(R.id.buttonCreateAccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText id = findViewById(R.id.editTextId);
                EditText name = findViewById(R.id.editTextName);
                EditText mobile = findViewById(R.id.editTextMobile);

                User user = new User();
                user.setId(Integer.parseInt(id.getText().toString()));
                user.setName(name.getText().toString());
                user.setMobile(mobile.getText().toString());

                Gson gson = new Gson();
                String json = gson.toJson(user);

                SharedPreferences sp = getSharedPreferences(
                        "com.example.app6.data",
                        Context.MODE_PRIVATE
                );
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("user",json);
                editor.apply();

                Intent i = new Intent(UserRegistrationActivity.this,HomeActivity.class);
                startActivity(i);

            }
        });
    }
}