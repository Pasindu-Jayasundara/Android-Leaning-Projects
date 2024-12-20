package com.example.app12;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app12.model.Contact;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

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

        findViewById(R.id.buttonSaveContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText mobile = findViewById(R.id.editTextMobile);
                EditText firstName = findViewById(R.id.editTextFirstName);
                EditText lastName = findViewById(R.id.editTextLastName);
                EditText city = findViewById(R.id.editTextCity);

                Contact contact = new Contact(
                        mobile.getText().toString(),
                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        city.getText().toString()
                );

                SharedPreferences sharedPreferences = getSharedPreferences("com.sample.app12.data", Context.MODE_PRIVATE);
                String contactListJson = sharedPreferences.getString("contactsJson", null);

                Gson gson = new Gson();
                ArrayList<Contact> contactArrayList;

                if(contactListJson == null){
                    contactArrayList = new ArrayList<>();
                }else{
                    Type type = new TypeToken<ArrayList<Contact>>(){}.getType();
                    contactArrayList = gson.fromJson(contactListJson, type);
                }
                contactArrayList.add(contact);
                sharedPreferences.edit().putString("contactsJson",gson.toJson(contactArrayList)).apply();

                mobile.setText("");
                firstName.setText("");
                lastName.setText("");
                city.setText("");

                firstName.requestFocus();

            }
        });
    }
}