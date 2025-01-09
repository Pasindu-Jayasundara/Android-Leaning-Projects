package com.example.angrybird.model;

import java.io.Serializable;

public class Bird implements Serializable {

    private float initialX;
    private float initialY;
    private float initialRotation;

    public Bird(float initialX, float initialY, float initialRotation) {
        this.initialX = initialX;
        this.initialY = initialY;
        this.initialRotation = initialRotation;
    }

    public float getInitialX() {
        return initialX;
    }

    public void setInitialX(float initialX) {
        this.initialX = initialX;
    }

    public float getInitialY() {
        return initialY;
    }

    public void setInitialY(float initialY) {
        this.initialY = initialY;
    }

    public float getInitialRotation() {
        return initialRotation;
    }

    public void setInitialRotation(float initialRotation) {
        this.initialRotation = initialRotation;
    }
}
