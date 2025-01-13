package com.example.app23;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BarChart barChart = findViewById(R.id.barchart);

        ArrayList<BarEntry> barEntryArrayList = new ArrayList<>();
        barEntryArrayList.add(new BarEntry(0,MainActivity4.attendance.getMonday()));
        barEntryArrayList.add(new BarEntry(10,MainActivity4.attendance.getTuesday()));
        barEntryArrayList.add(new BarEntry(20,MainActivity4.attendance.getWednesday()));
        barEntryArrayList.add(new BarEntry(30,MainActivity4.attendance.getThursday()));
        barEntryArrayList.add(new BarEntry(40,MainActivity4.attendance.getFriday()));
        barEntryArrayList.add(new BarEntry(50,MainActivity4.attendance.getSaturday()));
        barEntryArrayList.add(new BarEntry(60,MainActivity4.attendance.getSunday()));

        ArrayList<Integer> colorArrayList = new ArrayList<>();
        colorArrayList.add(getColor(R.color.green));
        colorArrayList.add(getColor(R.color.blue));
        colorArrayList.add(getColor(R.color.orange));
        colorArrayList.add(getColor(R.color.b));
        colorArrayList.add(getColor(R.color.a));
        colorArrayList.add(getColor(R.color.black));
        colorArrayList.add(getColor(R.color.purple));

        BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Attendance Count");
        barDataSet.setColors(colorArrayList);

        BarData barData = new BarData();
        barData.addDataSet(barDataSet);
        barData.setBarWidth(5);

        barChart.setPinchZoom(false);
        barChart.setScaleEnabled(false);
        barChart.animateY(1000, Easing.EaseInBounce);

        barChart.setFitBars(true);
        barChart.setData(barData);

        ArrayList<LegendEntry> legendEntryArrayList = new ArrayList<>();
        legendEntryArrayList.add(new LegendEntry(
                "Monday",
                Legend.LegendForm.CIRCLE,
                Float.NaN,
                Float.NaN,
                null,
                getColor(R.color.green)
        ));
        legendEntryArrayList.add(new LegendEntry("Tuesday", Legend.LegendForm.CIRCLE, Float.NaN,Float.NaN,null, getColor(R.color.blue)));
        legendEntryArrayList.add(new LegendEntry("Wednesday", Legend.LegendForm.CIRCLE, Float.NaN,Float.NaN,null, getColor(R.color.orange)));
        legendEntryArrayList.add(new LegendEntry("Thursday", Legend.LegendForm.CIRCLE, Float.NaN,Float.NaN,null, getColor(R.color.b)));
        legendEntryArrayList.add(new LegendEntry("Friday", Legend.LegendForm.CIRCLE, Float.NaN,Float.NaN,null, getColor(R.color.a)));
        legendEntryArrayList.add(new LegendEntry("Saturday", Legend.LegendForm.CIRCLE, Float.NaN,Float.NaN,null, getColor(R.color.black)));
        legendEntryArrayList.add(new LegendEntry("Sunday", Legend.LegendForm.CIRCLE, Float.NaN,Float.NaN,null, getColor(R.color.purple)));

        barChart.getLegend().setCustom(legendEntryArrayList);
        barChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        barChart.getLegend().setXEntrySpace(5);

        barChart.setDescription(null);

        barChart.invalidate();

    }
}