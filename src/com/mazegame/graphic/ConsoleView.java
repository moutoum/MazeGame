package com.mazegame.graphic;

import com.mazegame.game.DestructionPower;
import com.mazegame.game.Game;
import com.sun.deploy.util.StringUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.mazegame.graphic.GamePanel.WINDOW_HEIGHT;
import static com.mazegame.graphic.GamePanel.WINDOW_WIDTH;

public class ConsoleView extends View {

    private String command = "";
    private Font font = new Font(Font.MONOSPACED, Font.BOLD, 20);

    private Game game;

    public ConsoleView(Game game) {
        this.isTransparent = true;
        this.game = game;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setFont(font);
        graphics.setColor(new Color(255,255,255,100));
        graphics.fillRect(0, WINDOW_HEIGHT - 50, WINDOW_WIDTH, 50);
        graphics.setColor(Color.WHITE);
        graphics.drawString(command, 10, WINDOW_HEIGHT - 10);
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() >= 32 && e.getKeyChar() < 127) {
            command += e.getKeyChar();
        }
        if (e.getKeyChar() == '\b') {
            if (command.length() > 0) {
                command = command.substring(0, command.length() - 1);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            command = command.trim().replace("/", "");
            String[] cmd = command.split("\\s+");
            if (cmd[0].equals("tp")) {
                game.getPlayer().setPosition(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
            }

            if (cmd[0].equals("power")) {
                if (cmd[1].equals("destruction")) {
                    new DestructionPower().use(game);
                }
            }

            if (cmd[0].equals("light")) {
                game.getPlayer().setStrengthLight(Integer.parseInt(cmd[1]));
            }
            delete();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
