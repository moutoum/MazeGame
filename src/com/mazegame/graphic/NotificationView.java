package com.mazegame.graphic;

import java.awt.*;
import java.awt.event.KeyEvent;

public class NotificationView extends View {

    private long start;
    private long end;
    private Font font = new Font(Font.MONOSPACED, Font.BOLD, 50);
    private String text;

    NotificationView(String text) {
        this.isTransparent = true;
        this.isKeyImplemented = false;
        this.isUpdated = false;
        start = System.currentTimeMillis();
        end = start + 2000;
        this.text = text;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.RED);
        graphics.setFont(font);
        graphics.drawString(text, 100, 300);
    }

    @Override
    public void update() {
        if (System.currentTimeMillis() >= end) {
            delete();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
