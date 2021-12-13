package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends JPanel implements ActionListener {

    private JLabel title;
    private JButton button;

    private Graphics2D g2d;
    private JButton backButton;


    private Image backGroundImage;
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

    public SettingsMenu() {

        this.setVisible(true);
        initializeImages();
        initializeButtons();
        initializeLabels();
    }


    public void initializeImages() {
        backGroundImage = new ImageIcon("background-image.jpg").getImage();
        buttonEasyImage1 = new ImageIcon("button_easy.png");
        buttonEasyImage2 = new ImageIcon("button_easy1.png");
        buttonMediumImage1 = new ImageIcon("button_medium.png");
        buttonMediumImage2 = new ImageIcon("button_medium1.png");
        buttonHardImage1 = new ImageIcon("button_hard.png");
        buttonHardImage2 = new ImageIcon("button_hard1.png");
        buttonSoundOn1 = new ImageIcon("button_sound-on.png");
        buttonSoundOn2 = new ImageIcon("button_sound-on1.png");
        buttonSoundOff1 = new ImageIcon("button_sound-off.png");
        buttonSoundOff2 = new ImageIcon("button_sound-off1.png");
        buttonBack1 = new ImageIcon("button_back.png");
        buttonBack2 = new ImageIcon("button_back1.png");
    }

    public void initializeLabels() {
        title = new JLabel("SETTINGS");
        title.setBounds(40, 0, 800, 200);
    }

    public void initializeButtons() {
        backButton = new JButton();
        backButton.setBounds(450, 460, 400, 100);
        backButton.setBackground(Color.BLACK);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        g2d.drawImage(backGroundImage, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
