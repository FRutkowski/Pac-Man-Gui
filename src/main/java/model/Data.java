package model;

import controller.KeyHandler;

public class Data {

    private KeyHandler keyHandler = new KeyHandler();


    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }
}
