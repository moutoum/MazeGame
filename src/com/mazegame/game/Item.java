package com.mazegame.game;

public abstract class Item implements Usable {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
