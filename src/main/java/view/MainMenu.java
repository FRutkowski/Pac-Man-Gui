package view;

import model.Data;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainMenu extends JPanel {

    private JFrame mainFrame;
    private JButton startButton;
    private JButton settingsButton;
    private JButton topPlayersButton;
    private JButton exitButton;
    private Graphics2D g2d;
    private JLabel title;
    private Data data;
    private Image backGroundImage;
    private ImageIcon titleImage;
    private GridBagLayout gridBagLayout;
    private GridBagConstraints gridBagConstraints;
    private ImageIcon buttonStartImage1;
    private ImageIcon buttonStartImage2;
    private ImageIcon buttonSettingsImage1;
    private ImageIcon buttonSettingsImage2;
    private ImageIcon buttonTopPlayersImage1;
    private ImageIcon buttonTopPlayersImage2;
    private ImageIcon buttonExitImage1;
    private ImageIcon buttonExitImage2;

    public MainMenu(JFrame mainFrame, Data data) throws IOException {
        super();
        this.mainFrame = mainFrame;
        this.data = data;

        setLayout(null);
        setBounds(0, 0, 200, 200);
        setPreferredSize(new Dimension(200, 200));
        setVisible(true);

        initializeImages();
        initializeButtons();
        initializeLabels();
        paintComponents();
    }

    public void initializeImages() {
        backGroundImage = new ImageIcon("background-image.jpg").getImage();
        titleImage = new ImageIcon("title.png");
        buttonStartImage1 = new ImageIcon("button_start1.png");
        buttonStartImage2 = new ImageIcon("button_start2.png");
        buttonSettingsImage1 = new ImageIcon("button_settings1.png");
        buttonSettingsImage2 = new ImageIcon("button_settings2.png");
        buttonTopPlayersImage1 = new ImageIcon("button_topplayers1.png");
        buttonTopPlayersImage2 = new ImageIcon("button_topplayers2.png");
        buttonExitImage1 = new ImageIcon("button_exit1.png");
        buttonExitImage2 = new ImageIcon("button_exit2.png");
    }

    public void initializeLabels() {
        title = new JLabel(titleImage);
        title.setBounds(40, 0, 800, 200);
    }

    public void initializeButtons() {
        startButton = new JButton(buttonStartImage1);
        startButton.setBounds(450, 160, 400, 100);
        startButton.setBackground(Color.BLACK);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
//        startButton.setSize(30, 2);
        settingsButton = new JButton(buttonSettingsImage1);
        settingsButton.setBounds(450, 260, 400, 100);
        settingsButton.setBackground(Color.BLACK);
        settingsButton.setBorderPainted(false);
        settingsButton.setFocusPainted(false);
//        settingsButton.setSize(30, 2);
        topPlayersButton = new JButton(buttonTopPlayersImage1);
        topPlayersButton.setBounds(450, 360, 400, 100);
        topPlayersButton.setBackground(Color.BLACK);
        topPlayersButton.setBorderPainted(false);
        topPlayersButton.setFocusPainted(false);
//        topPlayersButton.setSize(30, 2);
        exitButton = new JButton(buttonExitImage1);
        exitButton.setBounds(450, 460, 400, 100);
        exitButton.setBackground(Color.BLACK);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
//        exitButton.setSize(30, 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.drawImage(backGroundImage, 0, 0, null);
    }

    public void paintComponents() {
        Font font = new Font("Calibri", Font.BOLD, 26);

        title.setFont(font);

        this.add(title);
        this.add(startButton);
        this.add(settingsButton);
        this.add(topPlayersButton);
        this.add(exitButton);
    }
}
