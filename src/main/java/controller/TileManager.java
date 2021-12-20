package controller;

import model.Tile;
import view.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TileManager {
    private Game game;
    private Tile[] tile;
    private int tileSize = 20;

    public TileManager(Game game) throws IOException {
        this.game = game;
        tile = new Tile[28];
        getTileImage();
    }

    public void getTileImage() throws IOException {
        tile[0] = new Tile();
        tile[0].setImage(ImageIO.read(new File("src/main/resources/images/block.png")));

        tile[1] = new Tile();
        tile[1].setImage(ImageIO.read(new File("src/main/resources/images/small_point.png")));

        tile[2] = new Tile();
        tile[2].setImage(ImageIO.read(new File("src/main/resources/images/big_point.png")));

        tile[3] = new Tile();
        tile[3].setImage(ImageIO.read(new File("src/main/resources/images/gate.png")));

        //5-8 animacje pacmana
        tile[4] = new Tile();
        tile[4].setImage(ImageIO.read(new File("src/main/resources/images/all.png")));

        tile[5] = new Tile();
        tile[5].setImage(ImageIO.read(new File("src/main/resources/images/left.png")));

        tile[6] = new Tile();
        tile[6].setImage(ImageIO.read(new File("src/main/resources/images/right.png")));

        tile[7] = new Tile();
        tile[7].setImage(ImageIO.read(new File("src/main/resources/images/down.png")));

        tile[8] = new Tile();
        tile[8].setImage(ImageIO.read(new File("src/main/resources/images/up.png")));

        tile[9] = new Tile();
        tile[9].setImage(ImageIO.read(new File("src/main/resources/images/black_tile.png")));
        //10-29 animacje wszystkich duchów
        getRedGhostImages();
        getPinkGhostImages();
        getAquamarineGhostImages();
        getOrangeGhostImages();
        getGreenGhostImages();
   }

    public void drawMap(Graphics2D g2d) {

    }

    public void getRedGhostImages() throws IOException {
        tile[10] = new Tile();
        tile[10].setImage(ImageIO.read(new File("src/main/resources/images/red-left-ghost1.png")));

        tile[11] = new Tile();
        tile[11].setImage(ImageIO.read(new File("src/main/resources/images/red-left-ghost2.png")));

        tile[12] = new Tile();
        tile[12].setImage(ImageIO.read(new File("src/main/resources/images/red-right-ghost1.png")));

        tile[13] = new Tile();
        tile[13].setImage(ImageIO.read(new File("src/main/resources/images/red-right-ghost2.png")));
    }

    public void getPinkGhostImages() throws IOException {
        tile[14] = new Tile();
        tile[14].setImage(ImageIO.read(new File("src/main/resources/images/pink-left-ghost1.png")));

        tile[15] = new Tile();
        tile[15].setImage(ImageIO.read(new File("src/main/resources/images/pink-left-ghost2.png")));

        tile[16] = new Tile();
        tile[16].setImage(ImageIO.read(new File("src/main/resources/images/pink-right-ghost1.png")));

        tile[17] = new Tile();
        tile[17].setImage(ImageIO.read(new File("src/main/resources/images/pink-right-ghost2.png")));
    }

    public void getAquamarineGhostImages() throws IOException {
        tile[18] = new Tile();
        tile[18].setImage(ImageIO.read(new File("src/main/resources/images/aquamarine-left-ghost1.png")));

        tile[19] = new Tile();
        tile[19].setImage(ImageIO.read(new File("src/main/resources/images/aquamarine-left-ghost2.png")));

        tile[20] = new Tile();
        tile[20].setImage(ImageIO.read(new File("src/main/resources/images/aquamarine-right-ghost1.png")));

        tile[21] = new Tile();
        tile[21].setImage(ImageIO.read(new File("src/main/resources/images/aquamarine-right-ghost2.png")));
    }

    public void getOrangeGhostImages() throws IOException {
        tile[22] = new Tile();
        tile[22].setImage(ImageIO.read(new File("src/main/resources/images/orange-left-ghost1.png")));

        tile[23] = new Tile();
        tile[23].setImage(ImageIO.read(new File("src/main/resources/images/orange-left-ghost2.png")));

        tile[24] = new Tile();
        tile[24].setImage(ImageIO.read(new File("src/main/resources/images/orange-right-ghost1.png")));

        tile[25] = new Tile();
        tile[25].setImage(ImageIO.read(new File("src/main/resources/images/orange-right-ghost2.png")));
    }

    public void getGreenGhostImages() throws IOException {
        tile[26] = new Tile();
        tile[26].setImage(ImageIO.read(new File("src/main/resources/images/green-left-ghost.png")));

        tile[27] = new Tile();
        tile[27].setImage(ImageIO.read(new File("src/main/resources/images/green-right-ghost.png")));
    }

    public Tile[] getTile() {
        return tile;
    }
    public int getTileSize() {
        return tileSize;
    }
}
