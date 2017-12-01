package com.mazegame.maze;

import java.io.Serializable;

public enum MazeNodeType implements Serializable {
    WALL(1, '#'),
    EMPTY(0, ' ');

    private int value;
    private char character;

    MazeNodeType(int value, char character) {
        this.value = value;
        this.character = character;
    }

    int getValue() { return this.value; }
    char getCharacter() { return this.character; }
}
