package view;

import model.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JPanel implements ActionListener {

    private JLabel title;
    private final Data data;

    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton backButton;

    private final MainMenu mainMenuPanel;

    private Image backGroundImage;
    private ImageIcon settingsTitleImage;
    private ImageIcon buttonEasyImage1;
    private ImageIcon buttonMediumImage1;
    private ImageIcon buttonHardImage1;
    private ImageIcon buttonBack1;

    public SettingsMenu(MainMenu mainMenuPanel, Data data) {
        super();
        this.mainMenuPanel = mainMenuPanel;
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
        settingsTitleImage = new ImageIcon("src/main/resources/images/settings_title.png");
        buttonEasyImage1 = new ImageIcon("src/main/resources/images/button_easy.png");
        buttonMediumImage1 = new ImageIcon("src/main/resources/images/button_medium.png");
        buttonHardImage1 = new ImageIcon("src/main/resources/images/button_hard.png");
        buttonBack1 = new ImageIcon("src/main/resources/images/button_back.png");
    }

    public void initializeLabels() {
        title = new JLabel(settingsTitleImage);
        title.setBounds(40, 0, 900, 200);
    }

    public void initializeButtons() {
        easyButton = new JButton(buttonEasyImage1);
        easyButton.setBounds(450, 160, 400, 100);
        easyButton.setBackground(Color.BLACK);
        easyButton.setBorderPainted(false);
        easyButton.setFocusPainted(false);
        easyButton.addActionListener(event -> data.setHardLevel(2));

        mediumButton = new JButton(buttonMediumImage1);
        mediumButton.setBounds(450, 260, 400, 100);
        mediumButton.setBackground(Color.BLACK);
        mediumButton.setBorderPainted(false);
        mediumButton.setFocusPainted(false);
        mediumButton.addActionListener(event -> data.setHardLevel(1.5F));

        hardButton = new JButton(buttonHardImage1);
        hardButton.setBounds(450, 360, 400, 100);
        hardButton.setBackground(Color.BLACK);
        hardButton.setBorderPainted(false);
        hardButton.setFocusPainted(false);
        hardButton.addActionListener(event -> data.setHardLevel(1));

        backButton = new JButton(buttonBack1);
        backButton.setBounds(450, 460, 400, 100);
        backButton.setBackground(Color.BLACK);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(event -> {
            setVisible(false);
            mainMenuPanel.setVisible(true);
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backGroundImage, 0, 0, null);
    }

    public void paintComponents() {
        add(title);
        add(easyButton);
        add(mediumButton);
        add(hardButton);
        add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
