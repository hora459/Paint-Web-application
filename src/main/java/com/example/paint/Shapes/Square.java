package com.example.paint.Shapes;

public class Square implements Shape {
    float x,y,length,strokewidth,scalex,scaley,rotation_angle;
    String fill,type,stroke;
    int id;

    @Override
    public void draw() {
        System.out.println("its square");
    }

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

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getStrokewidth() {
        return strokewidth;
    }

    public void setStrokewidth(float strokewidth) {
        this.strokewidth = strokewidth;
    }

    public float getScalex() {
        return scalex;
    }

    public void setScalex(float scalex) {
        this.scalex = scalex;
    }

    public float getScaley() {
        return scaley;
    }

    public void setScaley(float scaley) {
        this.scaley = scaley;
    }

    public float getRotation_angle() {
        return rotation_angle;
    }

    public void setRotation_angle(float rotation_angle) {
        this.rotation_angle = rotation_angle;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
