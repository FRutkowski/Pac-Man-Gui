package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private boolean upPressed;
    private boolean downPressed;
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean enterPressed;

    @Override
    public void keyTyped(KeyEvent event) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        System.out.println("is that work?");
        switch (keyCode) {
            case KeyEvent.VK_UP:
                System.out.println("is that work");
                upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("is that work");
                downPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("is that work");
                rightPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("is that work");
                leftPressed = true;
                break;
            case KeyEvent.VK_ENTER:
                enterPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_ENTER:
                enterPressed = false;
                break;
        }
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }
}
