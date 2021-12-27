package view;
import controller.TileManager;
import model.Data;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game extends JPanel {
    private final Data data;
    private final Player player = new Player();
    private Image backGroundImage;
    private ImageIcon buttonPlay;

    public JTextField name;
    public JButton playButton;
    public boolean drawMap = false;
    private final TileManager tileManager;

    public JLabel title;
    public JLabel gameOver;
    public JLabel gameOver2;
    public JLabel roundWon;
    public JLabel roundWon2;
    public JLabel scoreBoard;
    public JLabel remainingMoves;


    public Game(Data data) throws IOException {
        super();
        this.data = data;

        setVisible(true);
        setLayout(null);
        tileManager = new TileManager(this);

        initializeImages();
        initializeButtons();
        initializeLabels();
        initializeTextFields();
    }

    public void initializeImages() {
        backGroundImage = new ImageIcon("src/main/resources/images/background-black.png").getImage();
        buttonPlay = new ImageIcon("src/main/resources/images/button_play.png");
    }

    public void initializeLabels() {
         title = new JLabel("Enter your name");
         title.setFont(new Font("Arial", Font.BOLD, 27));
         title.setBounds(350, 20, 400, 40);
         title.setOpaque(true);

         title.setBackground(Color.BLACK);
         title.setForeground(Color.BLUE);
         this.add(title);

        initializeGameOverMessage();
        initializeRoundWonMessage();
        initializeScoreBoard();
    }

    public void initializeGameOverMessage() {
        gameOver = new JLabel("Game Over");
        gameOver.setFont(new Font("Arial", Font.BOLD, 27));
        gameOver.setBounds(390, 20, 400, 40);
        gameOver.setOpaque(true);

        gameOver.setBackground(Color.BLACK);
        gameOver.setForeground(Color.RED);
        gameOver.setVisible(false);
        this.add(gameOver);

        gameOver2 = new JLabel("Press enter to back to the menu");
        gameOver2.setFont(new Font("Arial", Font.PLAIN, 20));
        gameOver2.setBounds(310, 60, 400, 40);
        gameOver2.setOpaque(true);

        gameOver2.setBackground(Color.BLACK);
        gameOver2.setForeground(Color.RED);
        gameOver2.setVisible(false);
        this.add(gameOver2);
    }

    public void initializeRoundWonMessage() {
        roundWon = new JLabel("Round Won");
        roundWon.setFont(new Font("Arial", Font.BOLD, 27));
        roundWon.setBounds(390, 20, 400, 40);
        roundWon.setOpaque(true);

        roundWon.setBackground(Color.BLACK);
        roundWon.setForeground(Color.GREEN);
        roundWon.setVisible(false);
        this.add(roundWon);

        roundWon2 = new JLabel("Press enter to start next round");
        roundWon2.setFont(new Font("Arial", Font.PLAIN, 20));
        roundWon2.setBounds(320, 60, 400, 40);
        roundWon2.setOpaque(true);

        roundWon2.setBackground(Color.BLACK);
        roundWon2.setForeground(Color.GREEN);
        roundWon2.setVisible(false);
        this.add(roundWon2);
    }

    public void initializeScoreBoard() {
        scoreBoard = new JLabel(player.getCurrentPoints() + "");
        scoreBoard.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreBoard.setBounds(30, 60, 100, 40);
        scoreBoard.setOpaque(true);

        scoreBoard.setBackground(Color.BLACK);
        scoreBoard.setForeground(Color.YELLOW);
        scoreBoard.setVisible(false);
        this.add(scoreBoard);

        remainingMoves = new JLabel(String.valueOf((int) (350 * data.getHardLevel())));
        remainingMoves.setFont(new Font("Arial", Font.PLAIN, 20));
        remainingMoves.setBounds(30, 100, 100, 40);
        remainingMoves.setOpaque(true);

        remainingMoves.setBackground(Color.BLACK);
        remainingMoves.setForeground(Color.RED);
        remainingMoves.setVisible(false);
        this.add(remainingMoves);
    }

    public void initializeButtons() {
        playButton = new JButton(buttonPlay);
        playButton.setBounds(390, 160, 175, 60);
        playButton.setBackground(Color.BLACK);
        playButton.setBorderPainted(false);
        playButton.setFocusPainted(false);
        this.add(playButton);
    }

    public void initializeTextFields() {
        name = new JTextField(16);
        name.setFont(new Font("Arial", Font.BOLD, 27));
        name.setOpaque(true);

        name.setBackground(Color.RED);
        name.setForeground(Color.YELLOW);
        name.setBounds(295, 70, 360, 40);
        name.setHorizontalAlignment(JTextField.CENTER);
        this.add(name);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.BLACK);
        g2d.setColor(Color.BLACK);
        g2d.drawImage(backGroundImage, 0, 0, null);

        if (drawMap) {
            int col = 0;
            int row = 0;
            int x = 200;
            int y = 40;

            while (row < 23 && col < 26) {
                int tileNumber = data.getMap()[row][col];
                g2d.drawImage(tileManager.getTile()[tileNumber].getImage(), x, y, tileManager.getTileSize(), tileManager.getTileSize(), null);
                col++;
                x += tileManager.getTileSize();
                if (col == 26) {
                    col = 0;
                    x = 200;
                    row++;
                    y += tileManager.getTileSize();
                }
            }
        }
    }
}