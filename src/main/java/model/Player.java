package model;

import controller.Direction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Player {
    private int x, y;
    private int speed;
    private String name;

    private BufferedImage up, down, left, right;
    private Direction direction;

    public Player() throws IOException {
        this.x = 20;
        this.y = 20;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
