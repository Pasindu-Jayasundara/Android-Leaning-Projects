package com.example.app23;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.setNoDataText("This is a Pie Chart");

        Button btn = findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<PieEntry> pieEntryArrayList = new ArrayList<>();
                pieEntryArrayList.add(new PieEntry(5,"Android"));
                pieEntryArrayList.add(new PieEntry(20,"IOS"));
                pieEntryArrayList.add(new PieEntry(30,"React Native"));
                pieEntryArrayList.add(new PieEntry(45,"Flutter"));

                PieDataSet pieDataSet = new PieDataSet(pieEntryArrayList,null);
                ArrayList<Integer> colorArrayList = new ArrayList<>();
                colorArrayList.add(getColor(R.color.green));
                colorArrayList.add(getColor(R.color.blue));
                colorArrayList.add(getColor(R.color.purple));
                colorArrayList.add(getColor(R.color.orange));
                pieDataSet.setColors(colorArrayList);

                PieData pieData = new PieData();
                pieData.addDataSet(pieDataSet);
                pieData.setValueTextColor(getColor(R.color.green));

                pieChart.setCenterText("Mobile Development");
                pieChart.setCenterTextColor(getColor(R.color.green));

                pieChart.setDescription(null);
                pieChart.setData(pieData);
                pieChart.animateY(2000,Easing.EaseInCirc);
                pieChart.invalidate();

            }
        });

    }
}