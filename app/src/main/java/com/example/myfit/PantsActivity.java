package com.example.myfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class PantsActivity extends AppCompatActivity {
    ArrayList<SizeClass> sizeClasses=new ArrayList<>();
    ArrayList<String> items=new ArrayList<>();
    private String text;

    RecyclerView recyclerView;
    ReSizeAdapter reSizeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pants);

        //ocrActivity에서 사이즈 정보 받아오기
        Intent i=getIntent();
        Bundle bundle=i.getExtras();
        sizeClasses= (ArrayList<SizeClass>) bundle.get("sizeInfo");

        for (int j=0;j<sizeClasses.size();j++){
            items.add(sizeClasses.get(j).getSizeName());
        }
        System.out.println("itemNumber"+items.size());

        //리사이클러 뷰 어댑터
        recyclerView=findViewById(R.id.size_recycler);
        reSizeAdapter=new ReSizeAdapter(getApplicationContext(),items);
        recyclerView.setAdapter(reSizeAdapter);
    }
}
