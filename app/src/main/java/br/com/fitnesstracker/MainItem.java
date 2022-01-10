package br.com.fitnesstracker;

public class MainItem {

    private int id;
    private int drawableId;
    private  int color;
    private int textId;

    public MainItem(int id, int drawableId, int color, int textId) {
        this.id = id;
        this.drawableId = drawableId;
        this.color = color;
        this.textId = textId;
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

}
