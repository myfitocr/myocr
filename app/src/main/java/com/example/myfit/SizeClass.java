package com.example.myfit;

import java.util.ArrayList;

public class SizeClass {
    private String sizeName;
    private ArrayList<Float> sizeInfo;

    public SizeClass(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public ArrayList<Float> getSizeInfo() {
        return sizeInfo;
    }

    public void setSizeInfo(ArrayList<Float> sizeInfo) {
        this.sizeInfo = sizeInfo;
    }
}
