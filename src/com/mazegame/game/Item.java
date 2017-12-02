package com.mazegame.game;

import java.io.Serializable;

public abstract class Item implements Serializable {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void use(Player player);
}
