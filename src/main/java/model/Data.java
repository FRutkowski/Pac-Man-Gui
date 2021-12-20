package model;

import controller.KeyHandler;

public class Data {

    private KeyHandler keyHandler = new KeyHandler();
    private int tileSize = 25;
    private int[][] map;
    private int[][] mapElements;
    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int[][] getMapElements() {
        return mapElements;
    }

    public void setMapElements(int[][] mapElements) {
        this.mapElements = mapElements;
    }
}
