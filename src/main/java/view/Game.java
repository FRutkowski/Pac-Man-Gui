package view;
import controller.TileManager;
import model.Data;
import model.Player;
import model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Game extends JPanel {
    private final JFrame mainFrame;
    private Graphics2D g2d;
    private final Data data;

    private MainMenu mainMenu;
    private Image backGroundImage;
    private final Player player = new Player();
    private BufferedImage block;

    private final TileManager tileManager;
    private final boolean drawMap = true;
    private final Map<Position, Position> positionForEachIndex = new HashMap<>();
    private final Position[][] positionArray = new Position[23][26];

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
        System.out.println("coś tu się dzieje?");
        if (drawMap) {
            g2d.setBackground(Color.BLACK);
            g2d.setColor(Color.BLACK);
            g2d.drawImage(backGroundImage, 0, 0, null);
            int col = 0;
            int row = 0;
            int x = 200;
            int y = 40;
//            650 x
//            575y
            while (row < 23 && col < 26) {
                int tileNumber = data.getMap()[row][col];
                g2d.drawImage(tileManager.getTile()[tileNumber].getImage(), x, y, tileManager.getTileSize(), tileManager.getTileSize(), null);
                positionArray[row][col] = new Position(row, col);
                positionForEachIndex.put(positionArray[row][col], new Position(x, y));
                col++;
                x += tileManager.getTileSize();
                if (col == 26) {
                    col = 0;
                    x = 200;
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

    public Position[][] getPositions() {
        return positionArray;
    }

}