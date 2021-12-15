package view;

import model.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JPanel implements ActionListener {

    private JFrame mainFrame;
    private JLabel title;
    private JButton button;

    private Graphics2D g2d;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton backButton;

    private MainMenu mainMenuPanel;

    private Image backGroundImage;
    private ImageIcon settingsTitleImage;
    private ImageIcon buttonEasyImage1;
    private ImageIcon buttonEasyImage2;
    private ImageIcon buttonMediumImage1;
    private ImageIcon buttonMediumImage2;
    private ImageIcon buttonHardImage1;
    private ImageIcon buttonHardImage2;
    private ImageIcon buttonSoundOn1;
    private ImageIcon buttonSoundOn2;
    private ImageIcon buttonSoundOff1;
    private ImageIcon buttonSoundOff2;
    private ImageIcon buttonBack1;
    private ImageIcon buttonBack2;

    public SettingsMenu(MainMenu mainMenuPanel, JFrame mainFrame, Data data) {
        super();
        this.mainFrame = mainFrame;
        this.mainMenuPanel = mainMenuPanel;

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
        buttonEasyImage2 = new ImageIcon("src/main/resources/images/button_easy1.png");
        buttonMediumImage1 = new ImageIcon("src/main/resources/images/button_medium.png");
        buttonMediumImage2 = new ImageIcon("src/main/resources/images/button_medium1.png");
        buttonHardImage1 = new ImageIcon("src/main/resources/images/button_hard.png");
        buttonHardImage2 = new ImageIcon("src/main/resources/images/button_hard1.png");
        buttonSoundOn1 = new ImageIcon("src/main/resources/images/button_sound-on.png");
        buttonSoundOn2 = new ImageIcon("src/main/resources/images/button_sound-on1.png");
        buttonSoundOff1 = new ImageIcon("src/main/resources/images/button_sound-off.png");
        buttonSoundOff2 = new ImageIcon("src/main/resources/images/button_sound-off1.png");
        buttonBack1 = new ImageIcon("src/main/resources/images/button_back.png");
        buttonBack2 = new ImageIcon("src/main/resources/images/button_back1.png");
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

        mediumButton = new JButton(buttonMediumImage1);
        mediumButton.setBounds(450, 260, 400, 100);
        mediumButton.setBackground(Color.BLACK);
        mediumButton.setBorderPainted(false);
        mediumButton.setFocusPainted(false);

        hardButton = new JButton(buttonHardImage1);
        hardButton.setBounds(450, 360, 400, 100);
        hardButton.setBackground(Color.BLACK);
        hardButton.setBorderPainted(false);
        hardButton.setFocusPainted(false);

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
        g2d = (Graphics2D) g;
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
