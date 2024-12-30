package com.example.app15;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
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

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteHelper a = new SQLiteHelper(MainActivity.this,"app15.db",null,1);

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        SQLiteDatabase sqLiteDatabase = a.getWritableDatabase();
                        sqLiteDatabase.execSQL("INSERT INTO `user`(`name`,`mobile`,`city`) VALUES('Sahan','0740211671','Colombo')");

                    }
                }).start();

            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteHelper a = new SQLiteHelper(MainActivity.this,"app15.db",null,1);

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        SQLiteDatabase sqLiteDatabase = a.getReadableDatabase();
                        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user", new String[]{});

                        while (cursor.moveToNext()){

                            String name = cursor.getString(1);
                            Log.i("cursorlog",name);
                        }

                    }
                }).start();

            }
        });
    }
}

class SQLiteHelper extends SQLiteOpenHelper{

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("CREATE TABLE user (\n" +
//                "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
//                "    name   TEXT    NOT NULL,\n" +
//                "    mobile TEXT    NOT NULL,\n" +
//                "    city   TEXT    NOT NULL\n" +
//                ");");

        sqLiteDatabase.execSQL("CREATE TABLE `user` (\n" +
                "  `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `mobile` VARCHAR(45) NOT NULL,\n" +
                "  `city` VARCHAR(45) NOT NULL " +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}