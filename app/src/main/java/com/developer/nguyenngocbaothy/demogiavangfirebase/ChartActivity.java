package com.developer.nguyenngocbaothy.demogiavangfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    ArrayList arrUS = new ArrayList();
    ArrayList arrAu = new ArrayList();
    ArrayList arrVn = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Intent intent = getIntent();
        arrAu = intent.getStringArrayListExtra("dataAU");
        arrUS = intent.getStringArrayListExtra("dataUS");
        arrVn = intent.getStringArrayListExtra("dataVN");

        Toast.makeText(this, "Au-" + arrAu.size() + "US-" + arrUS.size() + "VN-" + arrVn.size(), Toast.LENGTH_SHORT).show();

    }
}
