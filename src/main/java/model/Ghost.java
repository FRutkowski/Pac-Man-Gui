package model;

import controller.Direction;

public class Ghost {
    private int row, col;
    private Direction latestDirection;
    private int tileIndexColor;

    public Ghost(int row, int col) {
        this.row = row;
        this.col = col;
        this.latestDirection = Direction.NOWHERE;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Direction getLatestDirection() {
        return latestDirection;
    }

    public void setLatestDirection(Direction latestDirection) {
        this.latestDirection = latestDirection;
    }

}
