package com.developer.nguyenngocbaothy.demogiavangfirebase;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    ArrayList arrUS = new ArrayList();
    ArrayList arrAu = new ArrayList();
    ArrayList arrVn = new ArrayList();

    LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        mChart = (LineChart) findViewById(R.id.chart);

        Intent intent = getIntent();
        arrAu = intent.getStringArrayListExtra("dataAU");
        arrUS = intent.getStringArrayListExtra("dataUS");
        arrVn = intent.getStringArrayListExtra("dataVN");

        //Toast.makeText(this, "Au-" + arrAu.size() + "US-" + arrUS.size() + "VN-" + arrVn.size(), Toast.LENGTH_SHORT).show();

        // chart
        setData(arrAu.size(), arrUS.size(), arrVn.size());
        mChart.animateX(1000);

    }

    private void setData(int countAu, int countUs, int countVn) {
        // australia
        ArrayList<Entry> yAu = new ArrayList<>();
        for(int i = 0; i < countAu; i++) {
            String s = arrAu.get(i).toString();
            Float number = new Float(s).floatValue();
            yAu.add(new Entry(i,  number ));
        }

        // USA
        ArrayList<Entry> yUs = new ArrayList<>();
        for(int i = 0; i < countUs; i++) {
            String s = arrUS.get(i).toString();
            Float number = new Float(s).floatValue();
            yUs.add(new Entry(i,  number ));
        }

        // VN
        ArrayList<Entry> yVn = new ArrayList<>();
        for(int i = 0; i < countVn; i++) {
            String s = arrVn.get(i).toString();
            Float number = new Float(s).floatValue();
            yVn.add(new Entry(i,  number ));
        }

        LineDataSet setAU, setUS, setVN;

        setAU = new LineDataSet(yAu, "Australia");

        setUS = new LineDataSet(yUs, "USA");
        setUS.setColor(Color.GREEN);

        setVN = new LineDataSet(yVn, "VietNam");
        setVN.setColor(Color.RED);

        LineData data = new LineData(setAU, setUS, setVN);

        mChart.setData(data);
    }
}
