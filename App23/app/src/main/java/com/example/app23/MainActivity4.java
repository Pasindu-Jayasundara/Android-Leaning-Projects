package com.example.app23;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app23.modal.Attendance;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    public static Attendance attendance = new Attendance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinner = findViewById(R.id.spinner1);

        ArrayList<String> days = new ArrayList<>();
        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                MainActivity4.this,
                R.layout.custom_dropdown_item,
                days
        );

        spinner.setAdapter(arrayAdapter);

        Button btn = findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText = findViewById(R.id.editTextText);
                String attendanceCount = editText.getText().toString();

                if(attendanceCount.trim().isEmpty()){
                    Toast.makeText(MainActivity4.this,"Please Enter Attendance Count",Toast.LENGTH_LONG).show();
                    return;
                }

                if(!isInteger(attendanceCount)){
                    Toast.makeText(MainActivity4.this,"Please Enter Valid Attendance Count",Toast.LENGTH_LONG).show();
                    return;
                }

                int count = Integer.parseInt(attendanceCount);

                String selectedDay = spinner.getSelectedItem().toString();
                switch (selectedDay){
                    case "Monday":
                        attendance.setMonday(count);
                        break;
                    case "Tuesday":
                        attendance.setTuesday(count);
                        break;
                    case "Wednesday":
                        attendance.setWednesday(count);
                        break;
                    case "Thursday":
                        attendance.setThursday(count);
                        break;
                    case "Friday":
                        attendance.setFriday(count);
                        break;
                    case "Saturday":
                        attendance.setSaturday(count);
                        break;
                    case "Sunday":
                        attendance.setSunday(count);
                        break;
                    default:
                        Toast.makeText(MainActivity4.this,"Please Select a Day",Toast.LENGTH_LONG).show();
                        break;
                }

                Toast.makeText(MainActivity4.this,"Saved Success",Toast.LENGTH_LONG).show();
                editText.setText("");
                spinner.requestFocus();

            }
        });
    }

    private boolean isInteger(String text){
        try{
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}