package com.example.paint.Shapes;

public class Circle implements Shape {
    float radius,x,y,strokewidth,scalex,scaley,rotation_angle;
    int id;
    String fill,type,stroke;

    @Override
    public void draw() {
        System.out.println("its circle");}


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
