package br.com.fitnesstracker;

import android.content.Intent;

public class MainItem {

    private int id;
    private int drawableId;
    private  int color;
    private int textId;
    private Intent intent;


    public MainItem(int id, int drawableId, int color, int textId, Intent intent) {
        this.id = id;
        this.drawableId = drawableId;
        this.color = color;
        this.textId = textId;
        this.intent = intent;
    }

    public int getId() {
        return id;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public int getColor() {
        return color;
    }

    public int getTextId() {
        return textId;
    }

    public Intent getIntent() {
        return intent;
    }
}
