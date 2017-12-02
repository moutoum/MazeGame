package com.mazegame.graphic;

import com.mazegame.game.Game;
import com.mazegame.game.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;

public class GameInfoView extends View {

    private Game game;
    private Font font = new Font(Font.MONOSPACED, Font.BOLD, 15);

    GameInfoView(Game game) {
        this.isKeyImplemented = false;
        this.isTransparent = true;
        this.isUpdated = false;
        this.game = game;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(new Color(0,0,0,127));
        graphics.fillRect(0,0,100,110);
        Player player = game.getPlayer();
        graphics.setFont(font);
        graphics.setColor(Color.RED);
        graphics.drawString("X:"+(int)player.getX(), 20, 20);
        graphics.drawString("Y:"+(int)player.getY(), 20, 40);
        graphics.drawString("Level:"+game.getLevel().getLevel(), 20, 60);
        long diff = (System.currentTimeMillis() - game.getBeginGame()) / 1000;
        graphics.drawString(String.format("Game time: %dh %dm %ds", diff/3600, (diff%3600)/60, diff%60), 20, 80);
        diff = (System.currentTimeMillis() - game.getLevel().getBeginLevel()) / 1000;
        graphics.drawString(String.format("Level time: %dh %dm %ds", diff/3600, (diff%3600)/60, diff%60), 20, 100);
    }

    @Override
    public void update() {

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
