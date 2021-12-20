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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Game extends JPanel {
    private JFrame mainFrame;

    private Graphics2D g2d;
    private Data data;

    private MainMenu mainMenu;
    private Image backGroundImage;
    private Player player = new Player();
    private BufferedImage block;
    private TileManager tileManager;
    private boolean drawMap = true;

    public Game(MainMenu mainMenu, JFrame mainFrame, Data data) throws IOException {
        super();
        this.mainFrame = mainFrame;
        this.data = data;
//        setBackground(Color.BLACK);
        setVisible(true);

        tileManager = new TileManager(this);
        initializeImages();
    }

    public void initializeImages() {
        backGroundImage = new ImageIcon("src/main/resources/images/background-black.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        System.out.println("hello there");
        if (drawMap) {
            g2d.setBackground(Color.BLACK);
            g2d.setColor(Color.BLACK);
            g2d.drawImage(backGroundImage, 0, 0, null);
            int col = 0;
            int row = 0;
            int x = 40;
            int y = 40;
//            650 x
//            575y
            while (row < 23 && col < 26) {
                int tileNumber = data.getMap()[row][col];
                g2d.drawImage(tileManager.getTile()[tileNumber].getImage(), x, y, tileManager.getTileSize(), tileManager.getTileSize(), null);
                col++;
                x += tileManager.getTileSize();

                if (col == 26) {
                    col = 0;
                    x = 40;
                    row++;
                    y += tileManager.getTileSize();
                }
            }
        }

//        BufferedImage image = GameController.getDirectionImage(player);
//        g2d.drawImage(image, player.getX(), player.getY(), 25, 25, null);
//        g2d.drawImage(block, 40, 40, 25, 25, null);
//        g2d.drawImage(block, 40, 65, 25, 25, null);
//        g2d.drawImage(block, 40, 90, 25, 25, null);
    }


}