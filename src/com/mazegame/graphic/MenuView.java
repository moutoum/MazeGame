package com.mazegame.graphic;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuView extends View {

    private static String[] Text = {"Play", "Quit"};
    private int selected = 0;
    private static final Font font = new Font(Font.MONOSPACED, Font.BOLD, 15);

    public MenuView(GameViewManager gm) {
        super(gm);
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setFont(font);
        for (int i = 0; i < MenuView.Text.length; i++) {
            if (selected == i) {
                graphics.setColor(Color.RED);
            } else {
                graphics.setColor(Color.WHITE);
            }
            graphics.drawString(MenuView.Text[i], 50, i * 20 + 50);
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
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (selected > 0) {
                selected -= 1;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (selected < MenuView.Text.length - 1) {
                selected += 1;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER && MenuView.Text[selected] == "Play") {
            this.gm.add(new GameView(this.gm));
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER && MenuView.Text[selected] == "Quit") {
            this.gm.remove(this);
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
