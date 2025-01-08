package com.example.app19;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView titleView = findViewById(R.id.inputTextTitle);
                TextView descriptionView = findViewById(R.id.inputTextDescription);
                TextView categoryView = findViewById(R.id.inputTextCategory);
                TextView brandView = findViewById(R.id.inputTextBrand);
                TextView quantityView = findViewById(R.id.inputTextQty);
                TextView priceView = findViewById(R.id.inputTextPrice);



                Toast.makeText(MainActivity.this,"",Toast.LENGTH_LONG).show();

            }
        });
    }
}