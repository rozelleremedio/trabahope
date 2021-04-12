package com.example.trabahopeapp;

public class MyCategory {
    private int catImage;
    private String catName;

    public MyCategory(int catImage, String catName) {
        this.catImage = catImage;
        this.catName = catName;
    }

    public MyCategory() {
        this.catImage = catImage;
        this.catName = catName;
    }



    public int getCatImage() {
        return catImage;
    }

    public void setCatImage(int catImage) {
        this.catImage = catImage;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
