package com.mazegame.maze;

import java.io.Serializable;

public class Maze implements Serializable {
    private MazeNode[][] maze;
    private int width;
    private int height;

    public Maze(int width, int height, MazeNode[][] maze) {
        this.maze = maze;
        this.width = width;
        this.height = height;
    }

    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    public MazeNode getAt(int x, int y) {
        if (x >= 0 && y >= 0 && x < width && y < height) {
            return maze[x][y];
        }
        return (null);
    }
}
