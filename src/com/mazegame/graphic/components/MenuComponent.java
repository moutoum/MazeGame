package com.mazegame.graphic.components;

import java.awt.*;

public class MenuComponent implements Component {

    private String[] menuFields;
    private int selected;

    public MenuComponent(String[] menuFields) {
        this.menuFields = menuFields;
    }

    @Override
    public void draw(Graphics2D graphics) {

    }
}
