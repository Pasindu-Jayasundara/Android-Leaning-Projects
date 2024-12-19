package com.example.app9;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    static String currentText = "";

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

        Log.i("App 9", "onCreate");

        TextView textView = findViewById(R.id.textView);
        textView.setText(MainActivity.currentText);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = findViewById(R.id.textView);
                MainActivity.currentText = "Hello";
                textView.setText(MainActivity.currentText);

            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle b) {
        super.onSaveInstanceState(b);

        TextView textView = findViewById(R.id.textView);
        b.putString("text",textView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle b) {
        super.onRestoreInstanceState(b);

        TextView textView = findViewById(R.id.textView);
        textView.setText(b.getString("text"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("App 9", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("App 9", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("App 9", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("App 9", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("App 9", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("App 9", "onDestroy");
    }
}