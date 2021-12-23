package model;

import controller.Direction;

public class Ghost {
    private int rowPosition, colPosition;
    private Direction latestDirection;
    private int tileIndexColor;
    private boolean nextImage = true;

    public Ghost(int row, int col, int tileIndexColor) {
        this.rowPosition = row;
        this.colPosition = col;
        this.tileIndexColor = tileIndexColor;
        this.latestDirection = Direction.NOWHERE;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(int row) {
        this.rowPosition = row;
    }

    public int getColPosition() {
        return colPosition;
    }

    public void setColPosition(int col) {
        this.colPosition = col;
    }

    public Direction getLatestDirection() {
        return latestDirection;
    }

    public void setLatestDirection(Direction latestDirection) {
        this.latestDirection = latestDirection;
    }

    public int getTileIndexColor() {
        return tileIndexColor;
    }

    public void setTileIndexColor(int tileIndexColor) {
        this.tileIndexColor = tileIndexColor;
    }

    public boolean isNextImage() {
        return nextImage;
    }

    public void setNextImage(boolean nextImage) {
        this.nextImage = nextImage;
    }
}
