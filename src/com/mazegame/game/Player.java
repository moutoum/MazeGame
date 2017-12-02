package com.mazegame.game;

import java.io.Serializable;

public class Player extends Entity {

    public static final int DEFAULT_STRENGTH_LIGHT = 3;

    public float Velocity = 0.05f;
    private boolean isMoving = false;
    private float beginX;
    private float beginY;
    private int strengthLight;


    public Player(int x, int y) {
        super(x, y, Orientation.EAST);
        setStrengthLight(DEFAULT_STRENGTH_LIGHT);
    }

    public int getStrengthLight() {
        return strengthLight;
    }

    public void setStrengthLight(int strengthLight) {
        this.strengthLight = (strengthLight < DEFAULT_STRENGTH_LIGHT) ? DEFAULT_STRENGTH_LIGHT : strengthLight;
    }

    public void move() {
        if ( this.isMoving ) {
            switch ( this.orientation ) {
                case EAST:
                    this.X += this.Velocity;
                    break;

                case WEST:
                    this.X -= this.Velocity;
                    break;

                case NORTH:
                    this.Y -= this.Velocity;
                    break;

                case SOUTH:
                    this.Y += this.Velocity;
                    break;
            }

            if ( (this.orientation == Orientation.EAST || this.orientation == Orientation.SOUTH) && (Math.floor(this.X) > beginX || Math.floor(this.Y) > beginY)) {
                this.isMoving = false;
                this.X = (float)Math.floor(this.X);
                this.Y = (float)Math.floor(this.Y);
            }

            else if ( (this.orientation == Orientation.WEST || this.orientation == Orientation.NORTH) && (Math.ceil(this.X) < beginX || Math.ceil(this.Y) < beginY)) {
                this.isMoving = false;
                this.X = (float)Math.ceil(this.X);
                this.Y = (float)Math.ceil(this.Y);
            }
        }
    }

    public void goRight() {
        if ( !this.isMoving ) {
            this.storePosition();
            this.orientation = Orientation.EAST;
            this.isMoving = true;
        }
    }

    public void goLeft() {
        if ( !this.isMoving ) {
            this.storePosition();
            this.orientation = Orientation.WEST;
            this.isMoving = true;
        }
    }

    public void goTop() {
        if ( !this.isMoving ) {
            this.storePosition();
            this.orientation = Orientation.NORTH;
            this.isMoving = true;
        }
    }

    public void goBottom() {
        if ( !this.isMoving ) {
            this.storePosition();
            this.orientation = Orientation.SOUTH;
            this.isMoving = true;
        }
    }

    private void storePosition() {
        beginX = X;
        beginY = Y;
    }

    public float getVelocity() {
        return Velocity;
    }

    public void setVelocity(float velocity) {
        Velocity = velocity;
    }

    @Override
    public void setOrientation(Orientation orientation) {
        if (!isMoving) {
            super.setOrientation(orientation);
        }
    }
}
