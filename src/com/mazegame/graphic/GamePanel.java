package com.mazegame.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 900;
    public static final int FPS = 60;

    private Thread thread;
    private BufferedImage image;
    private Graphics2D graphics;
    private boolean running = false;

    private final GameViewManager gm = View.getGm();

    public GamePanel() {
        super();
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setFocusable(true);
        requestFocus();
        View.CreateWithView(new MenuView());
    }

    public void addNotify() {
        super.addNotify();

        if (this.thread == null) {
            this.thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        this.image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.graphics = (Graphics2D) this.image.getGraphics();
        running = true;

        long timeStart;
        double timeElapse;
        long timeAwait;

        while (running) {

            if (gm.size() == 0) {
                this.running = false;
                return;
            }

            timeStart = System.nanoTime();

            this.update();
            this.draw();
            this.getGraphics().drawImage(this.image, 0, 0, null);

            timeElapse = (System.nanoTime() - timeStart) / 1000000.0;
            timeAwait = 1000 / FPS - (int) timeElapse;

            if (timeAwait > 0) {
                try {
                    Thread.sleep(timeAwait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void update() {
        if ( !gm.isEmpty() ) {
            int i;
            for (i = gm.size() - 1; !gm.get(i).isUpdated; i--);
            for (; i < gm.size(); i++) {
                gm.get(i).update();
            }
        }

    }

    private void draw() {
        if ( !gm.isEmpty() ) {
            this.graphics.setColor(Color.BLACK);
            this.graphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

            int i;
            for (i = gm.size() - 1; i >= 0 && gm.get(i).isTransparent; i--);
            for (; i < gm.size(); i++) {
                gm.get(i).draw(this.graphics);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (!gm.isEmpty()) {
            gm.lastElement().keyTyped(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gm.isEmpty()) {
            int i;
            for (i = gm.size() - 1; i >= 0 && !gm.get(i).isKeyImplemented; i--);
            gm.get(i).keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!gm.isEmpty()) {
            int i;
            for (i = gm.size() - 1; i >= 0 && !gm.get(i).isKeyImplemented; i--);
            gm.get(i).keyReleased(e);
        }
    }
}
