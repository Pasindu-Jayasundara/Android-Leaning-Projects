package com.example.app5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

        button3.setOnClickListener(view -> {
            button4.setVisibility(View.INVISIBLE);
            button4.setVisibility(View.GONE);
        });

        button4.setOnClickListener(view->{
            button3.setVisibility(View.INVISIBLE);
            button3.setVisibility(View.GONE);
        });
    }
}