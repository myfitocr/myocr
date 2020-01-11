package com.example.myfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ocrActivity extends AppCompatActivity {
    TessBaseAPI tess;
    String dataPath = "";
    ArrayList<SizeClass> sizeClasses=new ArrayList<>();
    SizeClass sizeClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ocr_activity);

        dataPath = getFilesDir() + "/tesseract/";
        checkFile(new File(dataPath+"tessdata/"),"kor");
        checkFile(new File(dataPath+"tessdata/"),"eng");

        String lang = "kor+eng";
        tess = new TessBaseAPI();
        tess.init(dataPath,lang);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test_13);
        bitmap = ARGBBitmap(bitmap);
        processImage(bitmap);
    }

    public void processImage(Bitmap bitmap){
        String result = null;
        tess.setImage(bitmap);
        result = tess.getUTF8Text();
        TextView textView = findViewById(R.id.text);
        textView.setText(result);
        String target="보세요\n";
        int target_num=result.indexOf(target);
        String size;
        size= result.substring(target_num+4);

        char endOfSize=getEndOfSize(size);
        int endOfSize_num=size.indexOf(endOfSize);
        String getSize=size.substring(0,endOfSize_num);
        sizeClasses=getSizeInfo(getSize);
        Intent intent=new Intent(getApplicationContext(),PantsActivity.class);
        intent.putExtra("sizeInfo",sizeClasses);
        startActivity(intent);
    }

    private char getEndOfSize(String size){
        for(char c:size.toCharArray()){
            if (c>=32 && c<=126 || c==10){
            }else {
                return c;
            }
        }
        return 0;
    }

    private ArrayList<SizeClass> getSizeInfo(String getSize){
        String[] array=getSize.split("\n");
        String[] getType=array[0].split(" ");

        if(getType.length==6){//바지
            for(int i=0;i<array.length;i++){
                String[] getSizeInfo=array[i].split(" ");
                sizeClass=new SizeClass(getSizeInfo[0]);

                ArrayList<Float> info=new ArrayList<>();
                for(int j=1;j<getSizeInfo.length;j++){
                    info.add(Float.parseFloat(getSizeInfo[j]));
                }

                sizeClass.setSizeInfo(info);
                sizeClasses.add(sizeClass);
            }
        }
        return sizeClasses;
    }

    private void checkFile(File dir,String lang){
        if(!dir.exists()&&dir.mkdirs()){
            copyFiles(lang);
        }
        if(dir.exists()){
            String datafilePath = dataPath + "/tessdata/"+lang + ".traineddata";
            File datafile= new File(datafilePath);
            if(!datafile.exists()){
                copyFiles(lang);
            }
        }
    }
    private void copyFiles(String lang) {
        try {
            String filepath = dataPath + "/tessdata/" + lang + ".traineddata";
            AssetManager assetManager = getAssets();
            InputStream instream = assetManager.open("tessdata/"+lang+".traineddata");
            OutputStream outstream = new FileOutputStream(filepath);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }
            outstream.flush();
            outstream.close();
            instream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Bitmap ARGBBitmap(Bitmap img) {
        return img.copy(Bitmap.Config.ARGB_8888,true);
    }
}
