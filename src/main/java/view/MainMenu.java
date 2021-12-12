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

    public MainMenu(JFrame mainFrame, Data data) throws IOException {
        super();
        this.mainFrame = mainFrame;
        this.data = data;

        setLayout(null);
        setBounds(0, 0, 200, 200);
//        title.setIcon();
//        gridBagLayout = new GridBagLayout();
//        gridBagConstraints = new GridBagConstraints();
//        gridBagConstraints.fill = SwingConstants.NORTH;
//        gridBagLayout.setConstraints(this, gridBagConstraints);
//        setLayout(gridBagLayout);

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
    }

    public void initializeLabels() {
        title = new JLabel(titleImage);
        title.setBounds(40, 0, 800, 200);
    }

    public void initializeButtons() {
        startButton = new JButton("Start");
        startButton.setBounds(550, 160, 100, 30);
//        startButton.setSize(30, 2);
        settingsButton = new JButton("Settings");
        settingsButton.setBounds(550, 210, 100, 30);
//        settingsButton.setSize(30, 2);
        topPlayersButton = new JButton("Top Players");
        topPlayersButton.setBounds(550, 260, 100, 30);
//        topPlayersButton.setSize(30, 2);
        exitButton = new JButton("Exit");
        exitButton.setBounds(550, 310, 100, 30);
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

//        gridBagConstraints.insets = new Insets(5, 2, 40, 2);

//        gridBagConstraints.gridx = 20;
//        gridBagConstraints.gridy = 0;

        this.add(title);
//        gridBagConstraints.gridx = 30;
//        gridBagConstraints.gridy = 1;

        this.add(startButton);
//        gridBagConstraints.gridx = 30;
//        gridBagConstraints.gridy = 2;

        this.add(settingsButton);
//        gridBagConstraints.gridx = 30;
//        gridBagConstraints.gridy = 3;

        this.add(topPlayersButton);

//        gridBagConstraints.gridx = 30;
//        gridBagConstraints.gridy = 4;
        this.add(exitButton);
    }
}
