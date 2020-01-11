package com.example.myfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class PantsActivity extends AppCompatActivity {
    ArrayList<SizeClass> sizeClasses=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pants);

        //ocrActivity에서 사이즈 정보 받아오기
        Intent i=getIntent();
        Bundle bundle=i.getExtras();

        sizeClasses= (ArrayList<SizeClass>) bundle.get("sizeInfo");
        System.out.println("pantsActivity"+sizeClasses.size());
    }
}
