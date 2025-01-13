package com.example.app23;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BarChart barChart = findViewById(R.id.barchart);

        ArrayList<BarEntry> barEntryArrayList = new ArrayList<>();
        barEntryArrayList.add(new BarEntry(0,25));
        barEntryArrayList.add(new BarEntry(10,60));
        barEntryArrayList.add(new BarEntry(20,10));
        barEntryArrayList.add(new BarEntry(30,40));

        ArrayList<BarEntry> barEntryArrayList2 = new ArrayList<>();
        barEntryArrayList2.add(new BarEntry(5,25));
        barEntryArrayList2.add(new BarEntry(15,60));
        barEntryArrayList2.add(new BarEntry(25,10));
        barEntryArrayList2.add(new BarEntry(35,40));

        BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Test");
        barDataSet.setColor(R.color.black);

        BarDataSet barDataSet2 = new BarDataSet(barEntryArrayList2,"Test2");

        ArrayList<Integer> colorArrayList = new ArrayList<>();
        colorArrayList.add(getColor(R.color.blue));
        colorArrayList.add(getColor(R.color.green));
        barDataSet2.setColors(colorArrayList);

        BarData barData = new BarData();
        barData.addDataSet(barDataSet);
        barData.addDataSet(barDataSet2);
        barData.setBarWidth(3f);
        //barData.setValueTextColor(R.color.black);


        // ---------- customize whole chart ---------------
        // remove zoom and scale
        barChart.setPinchZoom(false);
        barChart.setScaleEnabled(false);

        // animation for bars when load
        barChart.animateY(2000, Easing.EaseInCubic);

        // chart description
        Description description = new Description();
        description.setText("Hi Desc");
        description.setYOffset(-15f);
        barChart.setDescription(description);

        // bar width eka samanawa enawa
        barChart.setFitBars(true);

        barChart.setData(barData);

        ArrayList<LegendEntry> legendEntryArrayList = new ArrayList<>();
        legendEntryArrayList.add(new LegendEntry("A",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,getColor(R.color.black)));
        legendEntryArrayList.add(new LegendEntry("A+",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,getColor(R.color.black)));
        legendEntryArrayList.add(new LegendEntry("B",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,getColor(R.color.blue)));
        legendEntryArrayList.add(new LegendEntry("B+",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,getColor(R.color.blue)));
        legendEntryArrayList.add(new LegendEntry("C",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,getColor(R.color.green)));
        legendEntryArrayList.add(new LegendEntry("C+",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,getColor(R.color.green)));
        legendEntryArrayList.add(new LegendEntry("D+",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,getColor(R.color.green)));
        legendEntryArrayList.add(new LegendEntry("D",Legend.LegendForm.CIRCLE,Float.NaN,Float.NaN,null,getColor(R.color.green)));

        barChart.getLegend().setCustom(legendEntryArrayList);
        barChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        barChart.getLegend().setXEntrySpace(48);
        barChart.invalidate();
    }
}