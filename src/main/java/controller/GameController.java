package controller;

import model.Data;
import model.Player;
import view.Game;

public class GameController implements Runnable {
    private Game game;
    private Data data;
    private Player player;
    private Thread gameThread;
    private KeyHandler keyHandler;

    public GameController(Game game, Data data, Player player) {
        this.game = game;
        this.data = data;
        this.player = player;
        this.keyHandler = data.getKeyHandler();
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
}
