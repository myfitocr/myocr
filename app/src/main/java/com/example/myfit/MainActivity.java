package com.example.myfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    TessBaseAPI tess;
    String dataPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
