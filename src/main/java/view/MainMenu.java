package view;

import controller.GameController;
import model.Data;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenu extends JPanel {

    private JFrame mainFrame;
    private JLabel title;

    private Graphics2D g2d;
    private Data data;

    private JButton startButton;
    private JButton settingsButton;
    private JButton topPlayersButton;
    private JButton exitButton;

    private Image backGroundImage;
    private ImageIcon titleImage;
    private ImageIcon buttonStartImage1;
    private ImageIcon buttonStartImage2;
    private ImageIcon buttonSettingsImage1;
    private ImageIcon buttonSettingsImage2;
    private ImageIcon buttonTopPlayersImage1;
    private ImageIcon buttonTopPlayersImage2;
    private ImageIcon buttonExitImage1;
    private ImageIcon buttonExitImage2;

    private SettingsMenu settingsMenu;
    private TopPlayersMenu topPlayersMenu;

    private GameController gameController;
    private Player player;

    public MainMenu(JFrame mainFrame, Data data) throws IOException {
        super();
        this.mainFrame = mainFrame;
        this.data = data;

        setLayout(null);
        setBounds(0, 0, 200, 200);
        setVisible(true);

        initializeImages();
        initializeButtons();
        initializeLabels();
        paintComponents();
    }

    public void initializeImages() {
        backGroundImage = new ImageIcon("src/main/resources/images/background-image.jpg").getImage();
        titleImage = new ImageIcon("src/main/resources/images/title.png");
        buttonStartImage1 = new ImageIcon("src/main/resources/images/button_start1.png");
        buttonStartImage2 = new ImageIcon("src/main/resources/images/button_start2.png");
        buttonSettingsImage1 = new ImageIcon("src/main/resources/images/button_settings1.png");
        buttonSettingsImage2 = new ImageIcon("src/main/resources/images/button_settings2.png");
        buttonTopPlayersImage1 = new ImageIcon("src/main/resources/images/button_topplayers1.png");
        buttonTopPlayersImage2 = new ImageIcon("src/main/resources/images/button_topplayers2.png");
        buttonExitImage1 = new ImageIcon("src/main/resources/images/button_exit1.png");
        buttonExitImage2 = new ImageIcon("src/main/resources/images/button_exit2.png");
    }

    public void initializeLabels() {
        title = new JLabel(titleImage);
        title.setBounds(40, 0, 900, 200);
    }

    public void initializeButtons() {
        startButton = new JButton(buttonStartImage1);
        startButton.setBounds(450, 160, 400, 100);
        startButton.setBackground(Color.BLACK);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.addActionListener(event -> {
            try {
                toggleViewToGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        settingsButton = new JButton(buttonSettingsImage1);
        settingsButton.setBounds(450, 260, 400, 100);
        settingsButton.setBackground(Color.BLACK);
        settingsButton.setBorderPainted(false);
        settingsButton.setFocusPainted(false);
        settingsButton.addActionListener(event -> toggleViewToSettingsMenu());

        topPlayersButton = new JButton(buttonTopPlayersImage1);
        topPlayersButton.setBounds(450, 360, 400, 100);
        topPlayersButton.setBackground(Color.BLACK);
        topPlayersButton.setBorderPainted(false);
        topPlayersButton.setFocusPainted(false);
        topPlayersButton.addActionListener(event -> toggleViewToTopPlayersMenu());

        exitButton = new JButton(buttonExitImage1);
        exitButton.setBounds(450, 460, 400, 100);
        exitButton.setBackground(Color.BLACK);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(event -> System.exit(0));
    }


    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.drawImage(backGroundImage, 0, 0, null);
    }

    public void paintComponents() {
        this.add(title);
        this.add(startButton);
        this.add(settingsButton);
        this.add(topPlayersButton);
        this.add(exitButton);
    }

    private void toggleViewToSettingsMenu() {
        this.setVisible(false);
        if (settingsMenu == null) {
            settingsMenu = new SettingsMenu(this, mainFrame, data);
            mainFrame.add(settingsMenu);
        } else settingsMenu.setVisible(true);
    }

    private void toggleViewToTopPlayersMenu() {
        this.setVisible(false);
        if (topPlayersMenu == null) {
            topPlayersMenu = new TopPlayersMenu(this, mainFrame, data);
            mainFrame.add(topPlayersMenu);
        } else topPlayersMenu.setVisible(true);
    }

    private void toggleViewToGame() throws IOException {
        mainFrame.repaint();
        this.setVisible(false);

        player = new Player();
        Game game = new Game(this, mainFrame, data);

        mainFrame.add(game);
        gameController = new GameController(game, data, player);
        gameController.startGame();
    }

}
