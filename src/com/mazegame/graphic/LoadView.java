package com.mazegame.graphic;

import com.mazegame.game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;

public class LoadView extends View {
    private static File dir = new File("./save");
    private static final Font font = new Font(Font.MONOSPACED, Font.BOLD, 15);
    private static final Font fontTitle = new Font(Font.MONOSPACED, Font.BOLD, 20);

    private Game game;
    private File[] list;
    private int selected = 0;

    public LoadView() {
        isTransparent = true;
        list = dir.listFiles();
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(new Color(0,0,0,127));
        graphics.fillRect(300, 50, GamePanel.WINDOW_WIDTH - 50 - 300, GamePanel.WINDOW_HEIGHT - 100);

        graphics.setFont(fontTitle);
        graphics.setColor(Color.WHITE);
        graphics.drawString("Choose your game:", 350, 100);
        graphics.setFont(font);
        for (int i = 0; i < list.length; i++) {
            File f = list[i];
            if (f.isFile()) {

                graphics.setColor((i == selected) ? Color.RED : Color.WHITE);
                graphics.drawString(f.getName().replace(".msave", ""), 350, 150 + (i * 30));
            }
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
            if (selected < list.length - 1) {
                selected += 1;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            delete();
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Game game;
            try {
                FileInputStream fileIn = new FileInputStream("./save/"+list[selected].getName());
                ObjectInputStream in = new ObjectInputStream(fileIn);
                game = (Game) in.readObject();
                in.close();
                fileIn.close();
            } catch (Exception i) {
                addView(new NotificationView("Cannot load this save"));
                delete();
                return;
            }
            View gameView = new GameView(game);
            addView(gameView);
            delete();
        }
    }

    @Override
    public void addView(View view) {
        getGm().add(view);
        view.setParent(getParent());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
