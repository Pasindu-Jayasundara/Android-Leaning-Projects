package com.example.app5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        button2.setVisibility(View.INVISIBLE);

        button1.setOnClickListener(view -> {
//            button2.setVisibility(View.VISIBLE);
//            button1.setVisibility(View.INVISIBLE);

            button2.bringToFront();
        });

        button2.setOnClickListener(view -> {
/*
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.INVISIBLE);
*/

            button1.bringToFront();
        });
    }
}