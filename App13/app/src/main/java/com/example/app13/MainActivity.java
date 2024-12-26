package com.example.app13;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    File file = new File("data/data/com.example.app13/files/test.txt");
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    File file = new File("data/data/com.example.app13/files/test.txt");
                    FileWriter fw = new FileWriter(file);

                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Hello World");
                    bw.newLine();
                    bw.write("File Writing");
                    bw.flush();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

}