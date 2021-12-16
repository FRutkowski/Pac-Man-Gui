package view;
import controller.KeyHandler;
import model.Data;
import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    private JFrame mainFrame;

    private Graphics2D g2d;
    private Data data;

    private MainMenu mainMenu;
    private KeyHandler keyHandler;

    public Game(MainMenu mainMenu, JFrame mainFrame, Data data) {
        super();
        this.mainFrame = mainFrame;
        this.data = data;
        this.keyHandler = data.getKeyHandler();
        setBackground(Color.BLACK);
        setVisible(true);

        initializeImages();
    }

    public void initializeImages() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
    }

}