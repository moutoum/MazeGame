package com.mazegame.graphic;

import com.mazegame.game.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveView extends View {

    private static File dir = new File("./save");
    private static final Font font = new Font(Font.MONOSPACED, Font.BOLD, 15);

    private Game game;
    private File[] list;
    private int selected = 0;
    private String newSave = "<new>";

    SaveView(Game game) {
        this.game = game;
        list = dir.listFiles();
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);

        if (selected == 0) {
            graphics.setColor(Color.RED);
        }

        graphics.drawString(newSave, 50, 50);

        for (int i = 0; i < list.length; i++) {
            File f = list[i];
            if (f.isFile()) {
                if (i + 1 == selected) {
                    graphics.setColor(Color.RED);
                } else {
                    graphics.setColor(Color.WHITE);
                }
                graphics.drawString(f.getName().replace(".msave", ""), 50, 50 + ((i + 1) * 30));
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (selected == 0) {
            if (Character.isLetterOrDigit(e.getKeyChar())) {
                if (newSave.equals("<new>")) {
                    newSave = "";
                }

                newSave = newSave + e.getKeyChar();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && selected == 0 && !newSave.equals("<new>")) {
            newSave = newSave.substring(0, newSave.length() - 1);
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (selected > 0) {
                selected -= 1;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (selected < list.length) {
                selected += 1;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            delete();
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            View v = new NotificationView("Saving...");
            addView(v);
            try {
                String fileName = (selected == 0) ? newSave : list[selected - 1].getName().replace(".msave", "");

                new File("./save").mkdirs();
                FileOutputStream fileOut = new FileOutputStream("./save/"+fileName+".msave");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(game);
                out.close();
                fileOut.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
            v.delete();
            addView(new NotificationView("Saving OK!"));
            delete();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
