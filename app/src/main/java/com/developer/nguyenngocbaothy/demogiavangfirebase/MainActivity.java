package com.developer.nguyenngocbaothy.demogiavangfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtUS, edtAu, edtVN;
    Switch switchActive;
    Button btnChart;

    ArrayList arrUS = new ArrayList();
    ArrayList arrAu = new ArrayList();
    ArrayList arrVn = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUS = (EditText) findViewById(R.id.edtUS);
        edtAu = (EditText) findViewById(R.id.edtAu);
        edtVN = (EditText) findViewById(R.id.edtVN);
        switchActive = (Switch) findViewById(R.id.switch1);
        btnChart = (Button) findViewById(R.id.btnChart);

        final DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        edtUS.setEnabled(false);
        edtAu.setEnabled(false);
        edtVN.setEnabled(false);

        // update data
//        mDatabase.child("USA").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                try {
//                    edtUS.setText(dataSnapshot.getValue().toString());
//                } catch (Exception e) {
//                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        mDatabase.child("Australia").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                try {
//                    edtAu.setText(dataSnapshot.getValue().toString());
//                } catch (Exception e) {
//                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        mDatabase.child("VietNam").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                try {
//                    edtVN.setText(dataSnapshot.getValue().toString());
//                } catch (Exception e) {
//                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        mDatabase.child("Australia").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(MainActivity.this, "australia: " + dataSnapshot.getValue().toString() + "\n", Toast.LENGTH_SHORT).show();
                arrAu.add(dataSnapshot.getValue());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("USA").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(MainActivity.this, "USA: " + dataSnapshot.getValue().toString() + "\n", Toast.LENGTH_SHORT).show();
                arrUS.add(dataSnapshot.getValue());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase.child("VietNam").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(MainActivity.this, "VietNam: " + dataSnapshot.getValue().toString() + "\n", Toast.LENGTH_SHORT).show();
                arrVn.add(dataSnapshot.getValue());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        switchActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    edtUS.setEnabled(true);
                    edtAu.setEnabled(true);
                    edtVN.setEnabled(true);
                }else {
                    Toast.makeText(MainActivity.this, "update successfully!" + "\n" + edtUS.getText() + "-" + edtAu.getText() + "-" + edtVN.getText(), Toast.LENGTH_SHORT).show();

                    mDatabase.child("USA").push().setValue(edtUS.getText().toString().trim());
                    mDatabase.child("Australia").push().setValue(edtAu.getText().toString().trim());
                    mDatabase.child("VietNam").push().setValue(edtVN.getText().toString().trim());

                    edtUS.setText("");
                    edtAu.setText("");
                    edtVN.setText("");

                    edtUS.setEnabled(false);
                    edtAu.setEnabled(false);
                    edtVN.setEnabled(false);
                }
            }
        });

        // button chart
        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                intent.putExtra("dataAU", arrAu);
                intent.putExtra("dataUS", arrUS);
                intent.putExtra("dataVN", arrVn);
                startActivity(intent);
            }
        });


    }
}
