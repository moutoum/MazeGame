package com.mazegame.game;

import com.mazegame.maze.Maze;
import com.mazegame.maze.MazeNode;
import com.mazegame.maze.MazeNodeType;

public class DestructionPower extends Power {

    public static final long POWER_DURATION = 10000;

    public DestructionPower() {
        super(POWER_DURATION);
    }

    @Override
    public void use(Game game) {
        super.use(game);

        Player player = game.getPlayer();
        Maze maze = game.getLevel().getMaze();

        int x = (int) player.getX();
        int y = (int) player.getY();

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i > 0 && j > 0 && i < maze.getWidth() - 1 && j < maze.getHeight() - 1) {
                    MazeNode node = maze.getAt(i, j);
                    if (node != null && node.getType() == MazeNodeType.WALL) {
                        node.setType(MazeNodeType.EMPTY);
                    }
                }
            }
        }
    }
}
