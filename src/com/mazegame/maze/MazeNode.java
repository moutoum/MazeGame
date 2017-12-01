package com.mazegame.maze;

import java.io.Serializable;

public class MazeNode implements Serializable {
    private int x;
    private int y;
    private MazeNode parent;
    private MazeNodeType type;
    private int directions;

    public MazeNode(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
        this.type = MazeNodeType.EMPTY;
        this.directions = 1 | (1 << 1) | (1 << 2) | (1 << 3);
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public MazeNode getParent() { return this.parent; }
    public MazeNodeType getType() { return this.type; }
    public int getDirections() { return this.directions; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setParent(MazeNode parent) { this.parent = parent; }
    public void setType(MazeNodeType type) { this.type = type; }
    public void setDirections(int directions) { this.directions = directions; }
}
