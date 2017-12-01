package com.mazegame.game;

import com.mazegame.maze.Maze;
import com.mazegame.maze.MazeNodeType;
import java.io.Serializable;

public class Game implements Serializable {
    private int LevelNumber = 0;
    private Player player = null;
    private Level Level = null;
    private boolean isLevelEnd;
    private long beginGame;

    public Game() {
        this.player = new Player(1, 1);
        beginGame = System.currentTimeMillis();
    }

    public Game(Player player) {
        this.player = player;
    }

    public void update() {
        if ( isLevelEnd ) {
            this.nextLevel();
        }

        player.move();

        if ( this.getLevel().isEnd( (int) this.player.getX(), (int) this.player.getY()) ) {
            isLevelEnd = true;
        }
    }

    public boolean isLevelEnd() {
        return isLevelEnd;
    }

    public Level getLevel() {
        return (Level);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void start() {
        nextLevel();
    }

    public void nextLevel() {
        this.LevelNumber += 1;
        this.Level = new Level(LevelNumber);
        this.Level.generateLoots();
        this.isLevelEnd = false;
        this.player.setPosition(Level.getBeginX(), Level.getBeginY());
    }

    public long getBeginGame() {
        return beginGame;
    }

    public Chest isPlayerOnChest() {
        for ( Chest c : Level.getChests() ) {
            if ( c.getX() == player.getX() && c.getY() == player.getY() ) {
                return ( c );
            }
        }
        return ( null );
    }

    public void moveTop() {
        Maze maze = this.getLevel().getMaze();

        player.setOrientation(Orientation.NORTH);
        if (maze.getAt((int) player.getX(), (int)player.getY() - 1).getType() == MazeNodeType.EMPTY) {
            player.goTop();
        }
    }

    public void moveRight() {
        Maze maze = this.getLevel().getMaze();

        player.setOrientation(Orientation.EAST);
        if (maze.getAt((int) player.getX() + 1, (int)player.getY()).getType() == MazeNodeType.EMPTY) {
            player.goRight();
        }
    }

    public void moveBottom() {
        Maze maze = this.getLevel().getMaze();

        player.setOrientation(Orientation.SOUTH);
        if (maze.getAt((int) player.getX(), (int)player.getY() + 1).getType() == MazeNodeType.EMPTY) {
            player.goBottom();
        }
    }

    public void moveLeft() {
        Maze maze = this.getLevel().getMaze();

        player.setOrientation(Orientation.WEST);
        if (maze.getAt((int) player.getX() - 1, (int)player.getY()).getType() == MazeNodeType.EMPTY) {
            player.goLeft();
        }
    }
}
