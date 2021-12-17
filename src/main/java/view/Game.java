package view;
import controller.GameController;
import controller.KeyHandler;
import controller.TileManager;
import model.Data;
import model.Player;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    private JFrame mainFrame;

    private Graphics2D g2d;
    private Data data;

    private int tileSize;

    private MainMenu mainMenu;
    TileManager tileManager;
    private Image backGroundImage;
    private Player player = new Player();

    public Game(MainMenu mainMenu, JFrame mainFrame, Data data) {
        super();
        this.mainFrame = mainFrame;
        this.data = data;
//        setBackground(Color.BLACK);
        setVisible(true);

        tileManager = new TileManager();

        initializeImages();

    }

    public void initializeImages() {

        backGroundImage = new ImageIcon("src/main/resources/images/background-black.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.setBackground(Color.BLACK);
        g2d.setColor(Color.BLACK);
        g2d.drawImage(backGroundImage, 0, 0, null);
    }

}