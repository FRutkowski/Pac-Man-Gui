package controller;

import model.Data;
import model.Ghost;
import model.Player;
import view.Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
    private List<Ghost> ghosts = new ArrayList<>();

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
        game.playButton.addActionListener(event -> startGame());
    }

    public void startGame() {
        if (game.name.getText().length() < 2 || game.name.getText() == null) return;
        prepareGame();
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void prepareGame() {
        data.setName(game.name.getText());
        game.playButton.setVisible(false);
        game.title.setVisible(false);
        game.name.setVisible(false);
        game.drawMap = true;
        game.repaint();
    }

    @Override
    public void run() {
        map[17][21] = 6;
        initializeGhosts();
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

        if (isArrowPressed) {
            for (Ghost ghost : ghosts) {
                Direction direction = GameMechanicsUtils.choosePath(data.getMapElements(), ghost.getRowPosition(), ghost.getColPosition(), ghost.getLatestDirection());
                int imageIndex = (int) (Math.random() * ((ghost.getTileIndexColor() + 3) - ghost.getTileIndexColor() + 1) + ghost.getTileIndexColor());
                switch (direction) {
                    case UP:
                        ghostMoveUp(ghost, imageIndex);
                        break;
                    case DOWN:
                        ghostMoveDown(ghost, imageIndex);
                        break;
                    case LEFT:
                        ghostMoveLeft(ghost);
                        break;
                    case RIGHT:
                        ghostMoveRight(ghost);
                        break;
                }
            }
            game.repaint();
        }
        Thread.sleep(150);
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

    public void initializeGhosts() {
        map[11][14] = 14;
        map[11][13] = 20;
        map[11][11] = 25;
        ghosts.add(new Ghost(11, 14, 10));
        ghosts.add(new Ghost(11, 13, 14));
        ghosts.add(new Ghost(11, 11, 18));
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

    public void ghostMoveUp(Ghost ghost, int imageIndex) {
        if (ghost.getColPosition() == player.getColumn() && (ghost.getRowPosition() == player.getRow() || ghost.getRowPosition() - 1 == player.getRow())) {
//                            PlayerInteractListener.gameOver(data);
            //TODO add game over
            return;
        }

        map[ghost.getRowPosition() - 1][ghost.getColPosition()] = imageIndex;
        map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
//                        data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
//                        data.addAmountOfGhosts(ghost.getRowPosition() - 1, ghost.getColPosition(), 1);
        ghost.setRowPosition(ghost.getRowPosition() - 1);
        ghost.setLatestDirection(Direction.UP);
    }

    public void ghostMoveDown(Ghost ghost, int imageIndex) {
        if (ghost.getColPosition() == player.getColumn() && (ghost.getRowPosition() == player.getRow() || ghost.getRowPosition() + 1 == player.getRow())) {
//                            PlayerInteractListener.gameOver(data);
            //TODO add game over
            return;
        }

        map[ghost.getRowPosition() + 1][ghost.getColPosition()] = imageIndex;
        map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
//                        data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
//                        data.addAmountOfGhosts(ghost.getRowPosition() + 1, ghost.getColPosition(), 1);

        ghost.setRowPosition(ghost.getRowPosition() + 1);
        ghost.setLatestDirection(Direction.DOWN);
    }

    public void ghostMoveLeft(Ghost ghost) {
        if ((ghost.getColPosition() == player.getColumn() || ghost.getColPosition() - 1 == player.getColumn()) && ghost.getRowPosition() == player.getRow()) {
            return;
        }

        int imageIndex;
        if (ghost.isNextImage()) {
            imageIndex = ghost.getTileIndexColor();
            ghost.setNextImage(false);
        } else {
            imageIndex = ghost.getTileIndexColor() + 1;
            ghost.setNextImage(true);
        }

        if (ghost.getColPosition() >= 1) {
            map[ghost.getRowPosition()][ghost.getColPosition() - 1] = imageIndex;
            map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
            ghost.setColPosition(ghost.getColPosition() - 1);

        } else if (ghost.getColPosition() == 0) {
            map[ghost.getRowPosition()][25] = imageIndex;
            map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
            ghost.setColPosition(25);
        }

        ghost.setLatestDirection(Direction.LEFT);
    }

    public void ghostMoveRight(Ghost ghost) {
        if ((ghost.getColPosition() == player.getColumn() || ghost.getColPosition() + 1 == player.getColumn()) && ghost.getRowPosition() == player.getRow()) {
//            PlayerInteractListener.gameOver(data);
            return;
        }

        int imageIndex;
        if (ghost.isNextImage()) {
            imageIndex = ghost.getTileIndexColor() + 2;
            ghost.setNextImage(false);
        } else {
            imageIndex = ghost.getTileIndexColor() + 3;
            ghost.setNextImage(true);
        }

        if (ghost.getColPosition() <= 24) {
            map[ghost.getRowPosition()][ghost.getColPosition() + 1] = imageIndex;
            map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
            ghost.setColPosition(ghost.getColPosition() + 1);

        } else if (ghost.getColPosition() == 25) {
            map[ghost.getRowPosition()][0] = imageIndex;
            map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
            ghost.setColPosition(0);
        }

        ghost.setLatestDirection(Direction.RIGHT);
    }
}
