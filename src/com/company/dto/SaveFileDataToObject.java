package com.company.dto;

public class SaveFileDataToObject {
    private float x;
    private float y;
    public SaveFileDataToObject(){};

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public SaveFileDataToObject(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "SaveFileDataToObject{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
