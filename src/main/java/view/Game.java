package view;
import controller.Direction;
import controller.GameController;
import controller.KeyHandler;
import controller.TileManager;
import model.Data;
import model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Game extends JPanel {
    private JFrame mainFrame;

    private Graphics2D g2d;
    private Data data;


    private MainMenu mainMenu;
    TileManager tileManager;
    private Image backGroundImage;
    private Player player = new Player();
    private BufferedImage block;

    public Game(MainMenu mainMenu, JFrame mainFrame, Data data) throws IOException {
        super();
        this.mainFrame = mainFrame;
        this.data = data;
//        setBackground(Color.BLACK);
        setVisible(true);

        tileManager = new TileManager();

        initializeImages();

    }

    public void initializeImages() throws IOException {

        backGroundImage = new ImageIcon("src/main/resources/images/background-black.png").getImage();
        block = ImageIO.read(new File("src/main/resources/images/block.png"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.setBackground(Color.BLACK);
        g2d.setColor(Color.BLACK);
        g2d.drawImage(backGroundImage, 0, 0, null);

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

        g2d.drawImage(image, player.getX(), player.getY(), 25, 25, null);
        g2d.drawImage(block, 40, 40, 25, 25, null);
        g2d.drawImage(block, 40, 65, 25, 25, null);
        g2d.drawImage(block, 40, 90, 25, 25, null);
    }
}