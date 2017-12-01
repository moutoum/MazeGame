package com.mazegame.graphic;

import com.mazegame.game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InGameMenuView extends View {

    private Game game;
    private static String[] menuFields = {"Resume", "Save", "Quit"};
    private int selected = 0;

    InGameMenuView(GameViewManager gm, Game game) {
        super(gm);
        this.game = game;
        this.isTransparent = true;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(new Color(0,0,0,127));
        graphics.fillRect(50,50,GamePanel.WINDOW_WIDTH - 50, GamePanel.WINDOW_HEIGHT - 50);

        for (int i = 0; i < menuFields.length; i++) {
            if (i == selected) {
                graphics.setColor(Color.RED);
            } else {
                graphics.setColor(Color.WHITE);
            }

            graphics.drawString(menuFields[i], 100, 100 + (20 * i));
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            selected += 1;
            if (selected > menuFields.length - 1) {
                selected = menuFields.length - 1;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            selected -= 1;
            if (selected < 0) {
                selected = 0;
            }
        }

        if ( e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_ENTER && menuFields[selected].equals("Resume")) {
            gm.remove(this);
        }

        if ( e.getKeyCode() == KeyEvent.VK_ENTER && menuFields[selected].equals("Quit")) {
            gm.setSize(1);
            gm.trimToSize();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
