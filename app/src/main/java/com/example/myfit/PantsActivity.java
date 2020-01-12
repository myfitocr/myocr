package com.example.myfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PantsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    ArrayList<SizeClass> sizeClasses = new ArrayList<>();
    ArrayList<String> items = new ArrayList<>();
    ArrayList<CheckBox> checkBoxes = new ArrayList<>();

    SizeClass myFit = new SizeClass("myFit"); //사용자 사이즈 정보
    RecyclerView recyclerView;
    ReSizeAdapter reSizeAdapter;

    ArrayList<SizeClass> unchangedSize = new ArrayList<>();
    ArrayList<SizeClass> checkedSize = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pants);

        //ocrActivity에서 사이즈 정보 받아오기
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        sizeClasses = (ArrayList<SizeClass>) bundle.get("sizeInfo");

        for (int j = 0; j < sizeClasses.size(); j++) {
            items.add(sizeClasses.get(j).getSizeName());
        }

        CheckBox cb0 = findViewById(R.id.cb0);
        CheckBox cb1 = findViewById(R.id.cb1);
        CheckBox cb2 = findViewById(R.id.cb2);
        CheckBox cb3 = findViewById(R.id.cb3);
        CheckBox cb4 = findViewById(R.id.cb4);
        CheckBox cb5 = findViewById(R.id.cb5);
        CheckBox cb6 = findViewById(R.id.cb6);
        CheckBox cb7 = findViewById(R.id.cb7);
        CheckBox cb8 = findViewById(R.id.cb8);
        CheckBox cb9 = findViewById(R.id.cb9);

        checkBoxes.add(cb0);
        checkBoxes.add(cb1);
        checkBoxes.add(cb2);
        checkBoxes.add(cb3);
        checkBoxes.add(cb4);
        checkBoxes.add(cb5);
        checkBoxes.add(cb6);
        checkBoxes.add(cb7);
        checkBoxes.add(cb8);
        checkBoxes.add(cb9);

        for (int k = 0; k < 10; k++) {
            checkBoxes.get(k).setOnCheckedChangeListener(this);
            checkBoxes.get(k).setVisibility(View.INVISIBLE);
        }

        for (int l = 0; l < items.size(); l++) {
            checkBoxes.get(l).setVisibility(View.VISIBLE);
            checkBoxes.get(l).setText(items.get(l));
        }

        /**사용자 정보 sizeClass 에서 sizeInfo 받아오기**/
        ArrayList<Float> Info = new ArrayList<>();
        Info.add((float) 94);
        Info.add((float) 37.5);
        Info.add((float) 28);
        Info.add((float) 25);
        Info.add((float) 18);
        myFit.setSizeInfo(Info);

        checkedSize.add(myFit);

        //recycler adapter
        /**column 너비 조정 필요**/
        recyclerView=findViewById(R.id.recycler_size);
        reSizeAdapter=new ReSizeAdapter(getApplicationContext(),checkedSize);
        recyclerView.setAdapter(reSizeAdapter);

        //layout manager
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        LinearLayout linearLayout=findViewById(R.id.pantsImage);
        ViewEx viewEx = new ViewEx(getApplicationContext(),checkedSize);
        linearLayout.addView(viewEx);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //ocrActivity에서 사이즈 정보 받아오기
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        unchangedSize = (ArrayList<SizeClass>) bundle.get("sizeInfo");
        checkedSize.clear();
        checkedSize.add(myFit);

        //체크박스를 클릭해서 상태가 바뀌었을 경우 호출되는 callback method
        for (int m = 0; m < checkBoxes.size(); m++) {
            if (checkBoxes.get(m).isChecked()) {
                checkedSize.add(unchangedSize.get(m));
            }
        }

        recyclerView=findViewById(R.id.recycler_size);
        reSizeAdapter=new ReSizeAdapter(getApplicationContext(),checkedSize);
        recyclerView.setAdapter(reSizeAdapter);

        //layout manager
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        LinearLayout linearLayout=findViewById(R.id.pantsImage);
        linearLayout.removeAllViews();
        ViewEx viewEx = new ViewEx(getApplicationContext(),checkedSize);
        linearLayout.addView(viewEx);
    }
}

