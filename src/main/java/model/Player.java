package model;

import controller.Direction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Player {
    private int col, row;
    private int speed;
    private String name;
    private boolean isLeftDirection = false;
    private int currentPoints = 0;

    private BufferedImage up, down, left, right;
    private Direction direction;

    public Player() throws IOException {
        this.row = 17;
        this.col = 21;
        direction = Direction.LEFT;
        up = ImageIO.read(new File("src/main/resources/images/up.png"));
        down = ImageIO.read(new File("src/main/resources/images/down.png"));
        left = ImageIO.read(new File("src/main/resources/images/left.png"));
        right = ImageIO.read(new File("src/main/resources/images/right.png"));
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public BufferedImage getUp() {
        return up;
    }

    public BufferedImage getDown() {
        return down;
    }


    public BufferedImage getLeft() {
        return left;
    }


    public BufferedImage getRight() {
        return right;
    }

    public int getColumn() {
        return col;
    }

    public void setColumn(int x) {
        this.col = x;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int y) {
        this.row = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLeftDirection() {
        return isLeftDirection;
    }

    public void setLeftDirection(boolean leftDirection) {
        isLeftDirection = leftDirection;
    }

    public void addPoints(int points) {
        currentPoints += points;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

}
