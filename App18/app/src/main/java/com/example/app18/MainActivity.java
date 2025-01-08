package com.example.app18;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.zip.Inflater;

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

        Button btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                View customToastInflatedView = layoutInflater.inflate(R.layout.layout1, null, false);

                TextView textView = customToastInflatedView.findViewById(R.id.textView);
                textView.setText("Hello");

                Toast toast = new Toast(MainActivity.this);
                toast.setView(customToastInflatedView);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();

            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //new AlertDialog.Builder(MainActivity.this).setTitle("").setMessage("").show();

                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                View view1 = layoutInflater.inflate(R.layout.layout2,null,false);

                TextView textView1 = view1.findViewById(R.id.textView2);
                textView1.setText("Title");

                TextView textView2 = view1.findViewById(R.id.textView3);
                textView2.setText("Message");

                Button btn = view1.findViewById(R.id.button3);
                btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
                    }
                });

                new AlertDialog.Builder(MainActivity.this).setView(view1).show();
            }
        });

    }

}