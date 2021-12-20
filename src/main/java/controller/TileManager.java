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
        tile = new Tile[20];
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

        tile[5] = new Tile();
        tile[5].setImage(ImageIO.read(new File("src/main/resources/images/all.png")));

        tile[6] = new Tile();
        tile[6].setImage(ImageIO.read(new File("src/main/resources/images/left.png")));

        tile[6] = new Tile();
        tile[6].setImage(ImageIO.read(new File("src/main/resources/images/right.png")));

        tile[7] = new Tile();
        tile[7].setImage(ImageIO.read(new File("src/main/resources/images/down.png")));

        tile[8] = new Tile();
        tile[8].setImage(ImageIO.read(new File("src/main/resources/images/up.png")));

        tile[9] = new Tile();
        tile[9].setImage(ImageIO.read(new File("src/main/resources/images/black_tile.png")));
    }

    public void drawMap(Graphics2D g2d) {

    }

    public Tile[] getTile() {
        return tile;
    }
    public int getTileSize() {
        return tileSize;
    }
}
