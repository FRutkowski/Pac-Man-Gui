package view;

import controller.GameController;
import model.Data;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainMenu extends JPanel {

    private final JFrame mainFrame;
    private JLabel title;

    private final Data data;

    private JButton startButton;
    private JButton settingsButton;
    private JButton topPlayersButton;
    private JButton exitButton;

    private Image backGroundImage;
    private ImageIcon titleImage;
    private ImageIcon buttonStartImage1;
    private ImageIcon buttonSettingsImage1;
    private ImageIcon buttonTopPlayersImage1;
    private ImageIcon buttonExitImage1;

    private SettingsMenu settingsMenu;
    private TopPlayersMenu topPlayersMenu;

    public MainMenu(JFrame mainFrame, Data data) {
        super();
        this.mainFrame = mainFrame;
        this.data = data;

        setLayout(null);
        setBounds(0, 0, 200, 200);
        setVisible(true);

        initializeImages();
        initializeButtons();
        initializeLabels();
        addComponents();
    }

    public void initializeImages() {
        backGroundImage = new ImageIcon("src/main/resources/images/background-image.jpg").getImage();
        titleImage = new ImageIcon("src/main/resources/images/title.png");
        buttonStartImage1 = new ImageIcon("src/main/resources/images/button_start1.png");
        buttonSettingsImage1 = new ImageIcon("src/main/resources/images/button_settings1.png");
        buttonTopPlayersImage1 = new ImageIcon("src/main/resources/images/button_topplayers1.png");
        buttonExitImage1 = new ImageIcon("src/main/resources/images/button_exit1.png");
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backGroundImage, 0, 0, null);
    }

    public void addComponents() {
        this.add(title);
        this.add(startButton);
        this.add(settingsButton);
        this.add(topPlayersButton);
        this.add(exitButton);
    }

    private void toggleViewToSettingsMenu() {
        this.setVisible(false);
        if (settingsMenu == null) {
            settingsMenu = new SettingsMenu(this, data);
            mainFrame.add(settingsMenu);
        } else settingsMenu.setVisible(true);
    }

    private void toggleViewToTopPlayersMenu() {
        this.setVisible(false);
        if (topPlayersMenu == null) {
            topPlayersMenu = new TopPlayersMenu(this, data);
            mainFrame.add(topPlayersMenu);
        } else topPlayersMenu.setVisible(true);
    }

    private void toggleViewToGame() throws IOException {
        mainFrame.repaint();
        this.setVisible(false);

        Player player = new Player();
        Game game = new Game(data);

        mainFrame.add(game);
        new GameController(this, game, data, player);
    }

}
