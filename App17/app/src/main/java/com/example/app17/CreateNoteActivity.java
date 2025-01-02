package com.example.app17;

import android.content.ContentValues;
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

        Button btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText1 = findViewById(R.id.editText1);
                EditText editText2 = findViewById(R.id.editText2);

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

                            long id = writableDatabase.insert("notes", null, contentValues);
                            Log.i("NotebookLog","Note Created with ID:"+String.valueOf(id));

                            writableDatabase.close();

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
                    }).start();

                }
            }
        });
    }
}