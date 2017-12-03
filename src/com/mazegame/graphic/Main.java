package com.mazegame.graphic;

import javax.swing.*;

public final class Main extends JFrame {

    public static final String WINDOW_NAME = "Maze";

    public Main() {
        super(WINDOW_NAME);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public final void start() {
        System.out.println("Game starting....");
        this.setContentPane(new GamePanel());
        this.setVisible(true);
        this.pack();
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.start();
    }
}
