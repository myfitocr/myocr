package com.example.myfit;

import androidx.appcompat.app.AppCompatActivity;

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
    ArrayList<SizeClass> biggerSize=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pants);
        final Spinner sizeSpinner=findViewById(R.id.sizeSpinner);

        //ocrActivity에서 사이즈 정보 받아오기
        Intent i=getIntent();
        Bundle bundle=i.getExtras();
        sizeClasses= (ArrayList<SizeClass>) bundle.get("sizeInfo");

        for (int j=0;j<biggerSize.size();j++){
            items.add(biggerSize.get(j).getSizeName());
        }

        setspinner(sizeSpinner,items);
    }

    public String setspinner(final Spinner spinner,ArrayList<String> items){
        ArrayList<String> items_1 = items;
        ArrayAdapter myadapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,items_1);
        spinner.setAdapter(myadapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //spinner 값 받아오는 부분 다시 짜기
        return text;
    }
}
