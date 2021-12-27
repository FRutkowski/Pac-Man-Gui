package controller;

import controller.Direction;
import model.Data;
import model.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class GameMechanicsUtils {

    public static boolean canGoTo(int row, int col, int[][] map) {
        if (col > 25) return true;
        if (col < 0) return true;
        if (map[row][col] != 0 && map[row][col] != 3) {
            return true;
        } else {
            return false;
        }
    }

    public static int calculatePoints(int row, int col, int[][] map) {
        switch (map[row][col]) {
            case 1:
                return 10;
            case 2:
                return 100;
            case 9:
                return 0;
        }

        return 0;
    }

    public static Direction choosePath(int[][] map, int rowPositionGhost, int colPositionGhost, Direction latestPath) {
        int x = colPositionGhost;
        int y = rowPositionGhost;
        Set<Direction> orientantions = new HashSet<>();

        if (x - 1 < 0 && latestPath == Direction.LEFT) return Direction.LEFT;
        else if (x - 1 < 0 && latestPath == Direction.RIGHT) return Direction.RIGHT;
        if (x + 1 > 25 && latestPath == Direction.RIGHT) return Direction.RIGHT;
        else if (x + 1 > 25 && latestPath == Direction.LEFT) return Direction.LEFT;
        List<Direction> availablePaths = new ArrayList<>();
        if (map[y - 1][x] == 1 || map[y - 1][x] == 2 || map[y - 1][x] == 9 || map[y - 1][x] == 3) {
            availablePaths.add(Direction.UP);
            orientantions.add(Direction.VERTICAL);
        }

        if (map[y + 1][x] == 1 || map[y + 1][x] == 2 || map[y + 1][x] == 9) {
            availablePaths.add(Direction.DOWN);
            orientantions.add(Direction.VERTICAL);
        }

        if (map[y][x - 1] == 1 || map[y][x - 1] == 2 || map[y][x - 1] == 9) {
            availablePaths.add(Direction.LEFT);
            orientantions.add(Direction.HORIZONTAL);
        }

        if (map[y][x + 1] == 1 || map[y][x + 1] == 2 || map[y][x + 1] == 9) {
            availablePaths.add(Direction.RIGHT);
            orientantions.add(Direction.HORIZONTAL);
        }

        if (availablePaths.size() > 2 || (orientantions.contains(Direction.HORIZONTAL) && orientantions.contains(Direction.VERTICAL))) {
            Random r = new Random();
            return availablePaths.get(r.nextInt(((availablePaths.size() - 1)) + 1));
        }

        if (latestPath == Direction.NOWHERE || !canGhostGo(latestPath, map, y, x)) {
            return availablePaths.get(0);
        }

        return latestPath;
    }

    public static boolean canGhostGo(Direction direction, int[][] map, int row, int col) {
        switch (direction) {
            case UP:
                return canGoTo(row - 1, col, map);
            case DOWN:
                return canGoTo(row + 1, col, map);
            case LEFT:
                return canGoTo(row, col - 1, map);
            case RIGHT:
                return canGoTo(row, col + 1, map);
        }

        return false;
    }

    public static void writeScoreToFile(Player player) throws IOException {
        File topPlayers = new File("topPlayers.txt");
        topPlayers.createNewFile();


        List<String> linesFromFile = Files.readAllLines(Paths.get("topPlayers.txt"));
        PrintWriter writer = new PrintWriter(topPlayers);
        List<String> linesToWrite = new ArrayList<>();
        boolean foundLineForScore = false;
        int numberOfLine = 0;

        if (linesFromFile.size() == 0) {
            linesToWrite.add(++numberOfLine + ". " + player.getName() + " " + player.getCurrentPoints());
        }

        for (String currentLine : linesFromFile) {
            ++numberOfLine;
            String[] lineElements = currentLine.split(" ");
            if (lineElements.length > 0) {
                if (!foundLineForScore) {
                    if (player.getCurrentPoints() > Integer.parseInt(lineElements[2])) {
                        foundLineForScore = true;
                        linesToWrite.add(numberOfLine + ". " + player.getName() + " " + player.getCurrentPoints());
                        linesToWrite.add(++numberOfLine + ". " + lineElements[1] + " " + lineElements[2]);
                        continue;
                    }

                    linesToWrite.add(currentLine);
                } else {
                    linesToWrite.add(numberOfLine + ". " + lineElements[1] + " " + lineElements[2]);
                }
            }

        }

        if (!foundLineForScore && linesFromFile.size() != 0) {
            linesToWrite.add(++numberOfLine + ". " + player.getName() + " " + player.getCurrentPoints());
        }

        writer.print("");
        writer.close();
        PrintWriter writer2 = new PrintWriter(topPlayers);

        for (String line : linesToWrite) {
            writer2.println(line);
        }

        writer2.close();
    }
}
