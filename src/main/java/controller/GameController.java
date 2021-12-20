package controller;

import model.Data;
import model.Player;
import view.Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameController implements Runnable {
    private Game game;
    private Data data;
    private Player player;
    private Thread gameThread;
    private KeyHandler keyHandler;
    private int[][] mapElements;
    private int[][] map;
    private boolean isArrowPressed = false;

    public GameController(Game game, Data data, Player player) throws FileNotFoundException {
        this.game = game;
        this.data = data;
        this.player = player;
        this.keyHandler = new KeyHandler();
        game.addKeyListener(keyHandler);
        game.setFocusable(true);
        game.requestFocus();
        map = loadMap();
        data.setMap(map);
        mapElements = loadMap();
        data.setMapElements(mapElements);

    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        map[17][21] = 6;
//        data.setMap(map);

        while (gameThread != null) {
            try {
                update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() throws InterruptedException {
//            f600ff kolor różowy
//            #ff8d00 kolor pomarańczowy
//            ff0000 kolor czerwony
//            #00ffab
            isArrowPressed = false;
            if (keyHandler.isUpPressed()) {
                movePacManUp();
            }
            if (keyHandler.isDownPressed()) {
                movePacManDown();
            }
            if (keyHandler.isRightPressed()) {
                movePacManRight();
            }
            if (keyHandler.isLeftPressed()) {
                movePacManLeft();
            }
        game.repaint();
        Thread.sleep(100);
    }

    public static BufferedImage getDirectionImage(Player player) {
        BufferedImage image = null;
        switch (player.getDirection()) {
            case UP:
                image = player.getUp();
                break;
            case DOWN:
                image = player.getDown();
                break;
            case LEFT:
                image = player.getLeft();
                break;
            case RIGHT:
                image = player.getRight();
                break;
        }
        return image;
    }

    public static int[][] loadMap() throws FileNotFoundException {
        int[][] map = new int[23][26];
        File file = new File("pac-man-map.txt");
        Scanner scanner = new Scanner(file);
        int j = 0;
        String currentLine;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            for (int i = 0; i < 26; i++) {
                String numbers[] = currentLine.split(" ");
                map[j][i] = Integer.parseInt(numbers[i]);
            }
            j++;
        }
        return map;
    }

    public void movePacManUp() {
        if (GameMechanicsUtils.canGoTo(player.getRow() - 1, player.getColumn(), mapElements)) {
            player.addPoints(GameMechanicsUtils.calculatePoints(player.getRow() - 1, player.getColumn(), map));
            if (map[player.getRow()][player.getColumn()] != 4) {
                map[player.getRow() - 1][player.getColumn()] = 4;
            } else {
                map[player.getRow() - 1][player.getColumn()] = 8;
            }

            map[player.getRow()][player.getColumn()] = 9;
            mapElements[player.getRow()][player.getColumn()] = 9;
            player.setRow(player.getRow() - 1);
            isArrowPressed = true;
        }
    }

    public void movePacManDown() {
        if (GameMechanicsUtils.canGoTo(player.getRow() + 1, player.getColumn(), map)) {
            player.addPoints(GameMechanicsUtils.calculatePoints(player.getRow() + 1, player.getColumn(), mapElements));

            if (map[player.getRow()][player.getColumn()] != 4) {
                map[player.getRow() + 1][player.getColumn()] = 4;
            } else {
                 map[player.getRow() + 1][player.getColumn()] = 7;
            }

            map[player.getRow()][player.getColumn()] = 9;
            mapElements[player.getRow()][player.getColumn()] = 9;
            player.setRow(player.getRow() + 1);
            isArrowPressed = true;
        }
    }

    public void movePacManRight() {
        if (GameMechanicsUtils.canGoTo(player.getRow(), player.getColumn() + 1, map)) {
            if (player.getColumn() == 25) {
                player.addPoints(GameMechanicsUtils.calculatePoints(player.getRow(), 0, mapElements));

                if (map[player.getRow()][player.getColumn()] != 4) {
                    map[player.getRow()][0] = 4;
                } else {
                    map[player.getRow()][0] = 6;
                }

                map[player.getRow()][player.getColumn()] = 9;
                mapElements[player.getRow()][player.getColumn()] = 9;
                player.setColumn(0);
            } else {
                player.addPoints(GameMechanicsUtils.calculatePoints(player.getRow(), player.getColumn() + 1, mapElements));

                if (map[player.getRow()][player.getColumn()] != 4) {
                    map[player.getRow()][player.getColumn() + 1] = 4;
                } else {
                    map[player.getRow()][player.getColumn() + 1] = 6;
                }

                map[player.getRow()][player.getColumn()] = 9;
                mapElements[player.getRow()][player.getColumn()] = 9;
                player.setColumn(player.getColumn() + 1);
            }
            isArrowPressed = true;
        }
    }

    public void movePacManLeft() {
        if (GameMechanicsUtils.canGoTo(player.getRow(), player.getColumn() - 1, map)) {
            if (player.getColumn() == 0) {
                player.addPoints(GameMechanicsUtils.calculatePoints(player.getRow(), 25, mapElements));

                if (map[player.getRow()][player.getColumn()] != 4) {
                    map[player.getRow()][25] = 4;
                } else {
                    map[player.getRow()][25] = 5;
                }

                map[player.getRow()][player.getColumn()] = 9;
                mapElements[player.getRow()][player.getColumn()] = 9;
                player.setColumn(25);
            } else {
                player.addPoints(GameMechanicsUtils.calculatePoints(player.getRow(), player.getColumn() - 1, mapElements));

                if (map[player.getRow()][player.getColumn()] != 4) {
                    map[player.getRow()][player.getColumn() - 1] = 4;
                } else {
                    map[player.getRow()][player.getColumn() - 1] = 5;
                }

                map[player.getRow()][player.getColumn()] = 9;
                mapElements[player.getRow()][player.getColumn()] = 9;
                player.setColumn(player.getColumn() - 1);
            }
            isArrowPressed = true;
        }
    }

}
