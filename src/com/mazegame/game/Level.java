package com.mazegame.game;

import com.mazegame.maze.Maze;
import com.mazegame.maze.MazeGenerator;
import com.mazegame.maze.MazeNode;
import com.mazegame.maze.MazeNodeType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Level implements Serializable {
    public static float CHEST_RATIO_GENERATION = 0.2f;

    private Maze Maze = null;
    private ArrayList<Chest> chests = new ArrayList<>();
    private int Level;
    private int beginX;
    private int beginY;
    private int endX;
    private int endY;
    private long beginLevel;

    public Level(int level) {
        Maze = new MazeGenerator(11 + (level * 2), 11 + (level * 2)).get();
        beginX = 1;
        beginY = 1;
        endX = Maze.getWidth() - 2;
        endY = Maze.getHeight() - 2;
        Level = level;
        beginLevel = System.currentTimeMillis();
    }

    public int getLevel() {
        return Level;
    }

    public Maze getMaze() {
        return Maze;
    }

    public boolean isBegin(int x, int y) {
        return (x == beginX && y == beginY);
    }

    public boolean isEnd(int x, int y) {
        return (x == endX && y == endY);
    }

    public int getBeginX() {
        return beginX;
    }

    public int getBeginY() {
        return beginY;
    }

    public long getBeginLevel() {
        return beginLevel;
    }

    public ArrayList<Chest> getChests() {
        return chests;
    }

    public void generateLoots() {
        for (int x = 1; x < this.Maze.getWidth() - 1; x++) {
            for (int y = 1; y < this.Maze.getHeight() - 1; y++) {
                if (isBegin(x, y) || isEnd(x, y)) continue;
                // Check si c'est un emplacement libre
                if ( this.Maze.getAt(x, y).getType() == MazeNodeType.EMPTY) {
                    // check si c'est un cul de sac
                    int i = 0;
                    Orientation o = Orientation.NORTH;
                    if ( this.Maze.getAt(x + 1, y).getType() == MazeNodeType.WALL) i++; else o = Orientation.EAST;
                    if ( this.Maze.getAt(x - 1, y).getType() == MazeNodeType.WALL) i++; else o = Orientation.WEST;
                    if ( this.Maze.getAt(x, y + 1).getType() == MazeNodeType.WALL) i++; else o = Orientation.SOUTH;
                    if ( this.Maze.getAt(x, y - 1).getType() == MazeNodeType.WALL) i++; else o = Orientation.NORTH;

                    if ( i == 3 ) {
                        int randomNumber = 1 + new Random().nextInt(100);
                        if (randomNumber <= CHEST_RATIO_GENERATION * 100) {
                            this.chests.add(new Chest(x, y, o));
                        }
                    }
                }
            }
        }

        System.out.println("===== Chest list =====");
        for (Chest c : chests) {
            System.out.println(String.format(" - X(%d) Y(%d)", (int) c.getX(), (int) c.getY()));
        }
    }
}
