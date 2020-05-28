package com.example.dhl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ProgressActivity extends AppCompatActivity {
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Toolbar myToolBar = findViewById(R.id.toolBar);
        setSupportActionBar(myToolBar);
        myToolBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
        pieChart = findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.animateXY(1400,1400);
        getEntries();

        Description description = new Description();
        description.setText("Member Registration Data");
        description.setTextSize(10f);
        pieChart.setDescription(description); 

    }
    private void getEntries() {
        List<PieEntry>value = new ArrayList<>();
        value.add(new PieEntry(40f,"January"));
        value.add(new PieEntry(40f,"February"));
        value.add(new PieEntry(20f,"March"));

        PieDataSet pieDataSet = new PieDataSet(value,"Months");
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);

    }
}
