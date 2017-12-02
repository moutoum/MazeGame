package com.mazegame.game;

import java.io.Serializable;
import java.util.Random;

public class Chest extends Entity {

    private Item[] items = {new Torch(), new BoostSpeed()};
    private boolean isOpen = false;

    public Chest(float x, float y, Orientation o) {
        super(x, y, o);
    }

    public Item open() {
        if ( !isOpen() ) {
            this.isOpen = true;
            return (items[new Random().nextInt(items.length)]);
        }
        return ( null );
    }

    public boolean isOpen() {
        return isOpen;
    }
}
