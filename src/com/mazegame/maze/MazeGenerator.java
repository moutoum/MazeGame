package com.mazegame.maze;

import java.util.Random;

public class MazeGenerator {
    private final int width;
    private final int height;
    private MazeNode[][] maze;

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        init();
        MazeNode start = maze[1][1];
        start.setParent(start);
        MazeNode last = start;

        while ((last = link(last)) != start);
    }

    private void init() {
        this.maze = new MazeNode[this.width][this.height];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                maze[x][y] = new MazeNode(x, y);
                if (y * x % 2 == 0) {
                    maze[x][y].setType(MazeNodeType.WALL);
                }
            }
        }
    }

    private MazeNode link(MazeNode node) {
        if (node == null) {
            return null;
        }
        Random rand = new Random();

        while (node.getDirections() != 0) {
            int dir = 1 << (rand.nextInt(4));

            if (((~node.getDirections()) & dir) != 0) {
                continue;
            }

            node.setDirections(node.getDirections() & ~dir);

            int x = 0;
            int y = 0;
            switch (dir) {
                case 1:
                    if (node.getX() + 2 < this.width) {
                        x = node.getX() + 2;
                        y = node.getY();
                    } else continue;
                    break;

                case 2:
                    if (node.getY() + 2 < this.height) {
                        x = node.getX();
                        y = node.getY() + 2;
                    } else continue;
                    break;

                case 4:
                    if (node.getX() - 2 >= 0) {
                        x = node.getX() - 2;
                        y = node.getY();
                    } else continue;
                    break;

                case 8:
                    if (node.getY() - 2 >= 0) {
                        x = node.getX();
                        y = node.getY() - 2;
                    } else continue;
                    break;
            }

            MazeNode destination = this.maze[x][y];

            if (destination.getType() == MazeNodeType.EMPTY) {
                if (destination.getParent() != null) continue;
                destination.setParent(node);
                this.maze[node.getX() + ((x - node.getX()) / 2)][node.getY() + ((y - node.getY()) / 2)].setType(MazeNodeType.EMPTY);
                return destination;
            }
        }

        return node.getParent();
    }

    public void display() {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                System.out.print(this.maze[x][y].getType().getCharacter());
            }
            System.out.println();
        }
    }

    public Maze get() {
        return (new Maze(this.width, this.height, this.maze));
    }

}