package controller;

import model.Data;
import model.Ghost;
import model.Player;
import view.Game;
import view.MainMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController implements Runnable {
    private final Game game;
    private final Data data;
    private final MainMenu mainMenu;

    private int[][] mapElements;
    private int[][] map;

    private Thread gameThread;
    private final KeyHandler keyHandler;
    private boolean isArrowPressed = false;

    private final Player player;
    private final List<Ghost> ghosts = new ArrayList<>();
    private int requiredPoints = 2850;
    private int remainingNumberOfMoves;
    private boolean gameOver = false;

    public GameController(MainMenu mainMenu, Game game, Data data, Player player) {
        this.game = game;
        this.data = data;
        this.mainMenu = mainMenu;

        this.keyHandler = new KeyHandler();
        this.player = player;
        this.remainingNumberOfMoves = (int) (350 * data.getHardLevel());

        data.setRemainingMoves(remainingNumberOfMoves);
        game.addKeyListener(keyHandler);
        game.setFocusable(true);
        game.requestFocus();

        game.playButton.addActionListener(event -> {
            try {
                startGame();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void startGame() throws FileNotFoundException {
        map = loadMap();
        data.setMap(map);
        mapElements = loadMap();
        data.setMapElements(mapElements);

        if (game.name.getText().length() < 2 || game.name.getText() == null) return;
        player.setName(game.name.getText());
        prepareGame();
        gameThread = new Thread(this);
        gameThread.start();
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
                String[] numbers = currentLine.split(" ");
                map[j][i] = Integer.parseInt(numbers[i]);
            }
            j++;
        }

        return map;
    }

    public void prepareGame() {
        data.setName(game.name.getText());
        data.setAmountOfGhosts(new int [23][26]);

        game.playButton.setVisible(false);
        game.title.setVisible(false);
        game.name.setVisible(false);

        game.scoreBoard.setVisible(true);
        game.remainingMoves.setVisible(true);
        game.drawMap = true;
        game.repaint();
    }

    @Override
    public void run() {
        map[17][21] = 6;
        initializeGhosts();
        while (gameThread != null) {
            try {
                update();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }



    public void initializeGhosts() {
        map[11][14] = 14;
        map[11][13] = 20;
        map[11][11] = 25;
        map[11][12] = 23;
        map[9][13] = 26;

        ghosts.add(new Ghost(11, 14, 10));
        ghosts.add(new Ghost(11, 13, 14));
        ghosts.add(new Ghost(11, 11, 18));
        ghosts.add(new Ghost(11, 12, 22));
        ghosts.add(new Ghost(9, 13, 26));
    }

    public void update() throws InterruptedException, IOException {
        isArrowPressed = false;
        if (keyHandler.isUpPressed()) movePacManUp();
        if (keyHandler.isDownPressed()) movePacManDown();
        if (keyHandler.isRightPressed()) movePacManRight();
        if (keyHandler.isLeftPressed()) movePacManLeft();
        if (data.getAmountOfGhosts()[player.getRow()][player.getColumn()] > 0) gameOver();

        if (isArrowPressed && gameThread != null) {
            if (--remainingNumberOfMoves == -1) gameOver();
            game.remainingMoves.setText(String.valueOf(remainingNumberOfMoves));

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
                if (ghosts.isEmpty()) return;
            }
            game.repaint();
        }

        game.scoreBoard.setText(String.valueOf(player.getCurrentPoints()));
        if (player.getCurrentPoints() == requiredPoints) {
            roundWon();
            return;
        }

        Thread.sleep(500);
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

    public void ghostMoveUp(Ghost ghost, int imageIndex) throws IOException, InterruptedException {
        if (ghost.getColPosition() == player.getColumn() && (ghost.getRowPosition() == player.getRow() || ghost.getRowPosition() - 1 == player.getRow())) {
            gameOver();
            return;
        }

        map[ghost.getRowPosition() - 1][ghost.getColPosition()] = imageIndex;
        map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
        data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
        data.addAmountOfGhosts(ghost.getRowPosition() - 1, ghost.getColPosition(), 1);

        ghost.setRowPosition(ghost.getRowPosition() - 1);
        ghost.setLatestDirection(Direction.UP);
    }

    public void ghostMoveDown(Ghost ghost, int imageIndex) throws IOException, InterruptedException {
        if (ghost.getColPosition() == player.getColumn() && (ghost.getRowPosition() == player.getRow() || ghost.getRowPosition() + 1 == player.getRow())) {
            gameOver();
            return;
        }

        map[ghost.getRowPosition() + 1][ghost.getColPosition()] = imageIndex;
        map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
        data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
        data.addAmountOfGhosts(ghost.getRowPosition() + 1, ghost.getColPosition(), 1);

        ghost.setRowPosition(ghost.getRowPosition() + 1);
        ghost.setLatestDirection(Direction.DOWN);
    }

    public void ghostMoveLeft(Ghost ghost) throws IOException, InterruptedException {
        if ((ghost.getColPosition() == player.getColumn() || ghost.getColPosition() - 1 == player.getColumn()) && ghost.getRowPosition() == player.getRow()) {
            gameOver();
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
            data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
            data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition() - 1, 1);
            ghost.setColPosition(ghost.getColPosition() - 1);

        } else if (ghost.getColPosition() == 0) {
            map[ghost.getRowPosition()][25] = imageIndex;
            map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
            data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
            data.addAmountOfGhosts(ghost.getRowPosition(), 25, 1);
            ghost.setColPosition(25);
        }

        ghost.setLatestDirection(Direction.LEFT);
    }

    public void ghostMoveRight(Ghost ghost) throws IOException, InterruptedException {
        if ((ghost.getColPosition() == player.getColumn() || ghost.getColPosition() + 1 == player.getColumn()) && ghost.getRowPosition() == player.getRow()) {
            gameOver();
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
            data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
            data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition() + 1, 1);
            ghost.setColPosition(ghost.getColPosition() + 1);

        } else if (ghost.getColPosition() == 25) {
            map[ghost.getRowPosition()][0] = imageIndex;
            map[ghost.getRowPosition()][ghost.getColPosition()] = mapElements[ghost.getRowPosition()][ghost.getColPosition()];
            data.addAmountOfGhosts(ghost.getRowPosition(), ghost.getColPosition(), -1);
            data.addAmountOfGhosts(ghost.getRowPosition(), 0, 1);
            ghost.setColPosition(0);
        }

        ghost.setLatestDirection(Direction.RIGHT);
    }

    public void startNextRound() throws FileNotFoundException {
        game.roundWon.setVisible(false);
        game.roundWon2.setVisible(false);
        game.drawMap = true;
        gameOver = false;

        remainingNumberOfMoves = (int) (350 * data.getHardLevel());
        game.remainingMoves.setText(String.valueOf(remainingNumberOfMoves));

        map = loadMap();
        data.setMap(map);
        mapElements = loadMap();
        data.setMapElements(mapElements);

        data.setAmountOfGhosts(new int[23][26]);
        ghosts.clear();
        initializeGhosts();

        map[17][21] = 6;
        player.setRow(17);
        player.setColumn(21);

        game.repaint();
        requiredPoints = requiredPoints + 2850;
    }

    public void finishGame() throws IOException {
        GameMechanicsUtils.writeScoreToFile(player);
        game.setVisible(false);
        mainMenu.setVisible(true);
        gameOver = false;
        gameThread = null;
    }

    public void gameOver() throws InterruptedException, IOException {
        game.drawMap = false;
        game.repaint();
        gameOver = true;
        game.gameOver.setVisible(true);
        game.gameOver2.setVisible(true);

        while (gameOver) {
            Thread.sleep(1);
            if (keyHandler.isEnterPressed()) {
                finishGame();
            }
        }
    }

    public void roundWon() throws IOException, InterruptedException {
        game.drawMap = false;
        game.repaint();
        gameOver = true;
        game.roundWon.setVisible(true);
        game.roundWon2.setVisible(true);

        Thread.sleep(2000);
        while (gameOver) {
            Thread.sleep(1);
            if (keyHandler.isEnterPressed()) {
                startNextRound();
            } else if (keyHandler.isKeyPressed() && !keyHandler.isEnterPressed()) {
                finishGame();
            }
        }
    }
}

