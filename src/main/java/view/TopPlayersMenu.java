package view;

import model.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPlayersMenu extends JPanel implements ActionListener {

    private JLabel title;
    private Data data;
    private JButton backButton;

    private final MainMenu mainMenuPanel;
    private Image backGroundImage;
    private ImageIcon buttonBack1;
    private ImageIcon topPlayersTitleImage;

    public TopPlayersMenu(MainMenu mainMenu, Data data) {
        super();
        this.mainMenuPanel = mainMenu;

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
        topPlayersTitleImage = new ImageIcon("src/main/resources/images/topplayers_title.png");
        buttonBack1 = new ImageIcon("src/main/resources/images/button_back.png");
    }

    public void initializeLabels() {
        title = new JLabel(topPlayersTitleImage);
        title.setBounds(40, 0, 900, 200);
    }

    public void initializeButtons() {

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
        add(backButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
