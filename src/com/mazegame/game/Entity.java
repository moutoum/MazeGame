package com.mazegame.game;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    protected float X;
    protected float Y;
    protected Orientation orientation;

    Entity() {
        this.X = 0;
        this.Y = 0;
        this.orientation = Orientation.NORTH;
    }

    Entity(float x, float y) {
        this.X = x;
        this.Y = y;
        this.orientation = Orientation.NORTH;
    }

    Entity(float x, float y, Orientation orientation) {
        this.X = x;
        this.Y = y;
        this.orientation = orientation;
    }

    public void setPosition(float x, float y) {
        this.X = x;
        this.Y = y;
    }

    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }

    public void setX(float x) {
        X = x;
    }

    public void setY(float y) {
        Y = y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
