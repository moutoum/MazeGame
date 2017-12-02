package com.mazegame.graphic;

import com.mazegame.game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MenuView extends View {

    private static String[] Text = {"Play", "Load", "Quit"};
    private int selected = 0;
    private static final Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
    private BufferedImage background;

    public MenuView() {
        try {
            background = ImageIO.read(new FileInputStream("./ressources/background2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        FontMetrics fontMetrics = graphics.getFontMetrics(font);
        graphics.setFont(font);

        graphics.drawImage(background, 0, 0, GamePanel.WINDOW_WIDTH, GamePanel.WINDOW_HEIGHT, null);

        for (int i = 0; i < MenuView.Text.length; i++) {
            if (selected == i) {
                graphics.setColor(Color.RED);
            } else {
                graphics.setColor(Color.WHITE);
            }
            graphics.drawString(MenuView.Text[i], (GamePanel.WINDOW_WIDTH / 2) - (fontMetrics.stringWidth(MenuView.Text[i]) / 2), (GamePanel.WINDOW_HEIGHT / 2) - ((Text.length * (40)) / 2) + (i * 40));
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

        if (e.getKeyCode() == KeyEvent.VK_ENTER && MenuView.Text[selected].equals("Play")) {
            addView(new GameView());
            //this.gm.add(new GameView(this, this.gm));
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER && MenuView.Text[selected].equals("Load")) {
            addView(new LoadView());
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER && MenuView.Text[selected].equals("Quit")) {
            this.delete();
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
