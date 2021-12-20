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

    public GameController(Game game, Data data, Player player) throws FileNotFoundException {
        this.game = game;
        this.data = data;
        this.player = player;
        this.keyHandler = new KeyHandler();
        game.addKeyListener(keyHandler);
        game.setFocusable(true);
        game.requestFocus();
        loadMap(data);
    }

    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        update();
    }

    public void update() {
        game.addKeyListener(keyHandler);
        while (gameThread != null) {
            if (keyHandler.isUpPressed()) {
                player.setDirection(Direction.UP);
            }
            if (keyHandler.isDownPressed()) {
                player.setDirection(Direction.DOWN);
            }
            if (keyHandler.isRightPressed()) {
                player.setDirection(Direction.RIGHT);
            }
            if (keyHandler.isLeftPressed()) {
                player.setDirection(Direction.LEFT);
            }
        }
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

    public static void loadMap(Data data) throws FileNotFoundException {
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
        data.setMap(map);
    }
}
