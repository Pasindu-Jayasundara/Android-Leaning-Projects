package com.example.app17;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app17.model.SQLiteHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNoteActivity extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editText1 = findViewById(R.id.editText1);
        EditText editText2 = findViewById(R.id.editText2);

        Intent intent = getIntent();
        if(intent.hasExtra("id")){
            id = intent.getStringExtra("id");
        }
        if(intent.hasExtra("title")){
            editText1.setText(intent.getStringExtra("title"));
        }
        if(intent.hasExtra("content")){
            editText2.setText(intent.getStringExtra("content"));
        }

        Button btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editText1.getText().toString().isEmpty()){
                    Toast.makeText(CreateNoteActivity.this, "Please Enter Title", Toast.LENGTH_SHORT).show();
                }else if(editText2.getText().toString().isEmpty()){
                    Toast.makeText(CreateNoteActivity.this, "Please Enter Content", Toast.LENGTH_SHORT).show();
                }else{

                        SQLiteHelper sqLiteHelper = new SQLiteHelper(
                                CreateNoteActivity.this,
                                "mynotebook.db",
                                null,
                                1
                        );

                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                SQLiteDatabase writableDatabase = sqLiteHelper.getWritableDatabase();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

                                ContentValues contentValues = new ContentValues();
                                contentValues.put("title", editText1.getText().toString());
                                contentValues.put("content", editText2.getText().toString());
                                contentValues.put("date_created", format.format(new Date()));

                                if(id!=null){

                                    int count = writableDatabase.update(
                                            "notes",
                                            contentValues,
                                            "`id`=?",
                                            new String[]{id}
                                    );
                                    Log.i("NotebookLog","Note Updated, Count: "+String.valueOf(count));
                                    Toast.makeText(CreateNoteActivity.this, "Note Updated", Toast.LENGTH_SHORT).show();

                                }else{
                                    long id = writableDatabase.insert(
                                            "notes",
                                            null,
                                            contentValues
                                    );
                                    Log.i("NotebookLog","Note Created with ID:"+String.valueOf(id));

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            editText1.setText("");
                                            editText2.setText("");

                                            editText1.requestFocus();

                                            Toast.makeText(CreateNoteActivity.this, "Note Created", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                writableDatabase.close();

                            }
                        }).start();

                }
            }
        });
    }
}