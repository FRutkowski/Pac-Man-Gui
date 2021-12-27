package view;

import model.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TopPlayersMenu extends JPanel implements ActionListener {

    private JLabel title;
    private Data data;
    private JButton backButton;

    private final MainMenu mainMenuPanel;
    private Image backGroundImage;
    private ImageIcon buttonBack1;
    private ImageIcon topPlayersTitleImage;
    private final List<JLabel> topPlayers = new ArrayList<JLabel>();

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
        List<String> linesFromFile = null;

        try {
            linesFromFile = Files.readAllLines(Paths.get("topPlayers.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        int i = 0;
        int y = 160;
        for (String line : linesFromFile) {
            if (i == 10) break;
            createLabelAndAddToList(line, y, i);
            y += 40;
            i++;
        }
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

    public void refreshTopPlayers() {
        List<String> linesFromFile = null;

        try {
            linesFromFile = Files.readAllLines(Paths.get("topPlayers.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        int y = 160;
        assert linesFromFile != null;
        for (String line : linesFromFile) {
            if (i == 10) break;
            if (topPlayers.get(i) != null) {
                topPlayers.get(i).setText(line);
            } else {
                createLabelAndAddToList(line, y, i);
            }

            y += 40;
            i++;
        }
    }

    public void createLabelAndAddToList(String line, int y, int i) {
        JLabel playerScore = new JLabel(line);
        playerScore.setFont(new Font("Arial", Font.BOLD, 15));
        playerScore.setBounds(280, y, 185, 40);
        playerScore.setOpaque(true);
        playerScore.setBackground(Color.BLACK);
        playerScore.setForeground(Color.CYAN);
        playerScore.setVisible(true);
        this.add(playerScore);
        topPlayers.add(playerScore);
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
