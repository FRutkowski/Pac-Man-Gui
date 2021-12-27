package model;

import controller.KeyHandler;

public class Data {

    private KeyHandler keyHandler = new KeyHandler();
    private int tileSize = 25;
    private int[][] map;
    private int[][] mapElements;
    private int[][] amountOfGhosts = new int[23][26];
    private String name = "Player";
    private float hardLevel = 1;
    private int remainingMoves = 100;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int[][] getAmountOfGhosts() {
        return amountOfGhosts;
    }

    public void addAmountOfGhosts(int y, int x, int i) {
        this.amountOfGhosts[y][x] += i;
    }

    public void setAmountOfGhosts(int [][] amountOfGhosts) {
        this.amountOfGhosts = amountOfGhosts;
    }

    public float getHardLevel() {
        return hardLevel;
    }

    public void setHardLevel(float hardLevel) {
        this.hardLevel = hardLevel;
    }

    public int getRemainingMoves() {
        return remainingMoves;
    }

    public void setRemainingMoves(int remainingMoves) {
        this.remainingMoves = remainingMoves;
    }
}
