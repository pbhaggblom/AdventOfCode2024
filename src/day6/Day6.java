package day6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 {

    char[][] map;
    int mapWidth = 0;
    int numObstructions;

    public Day6() {

        List<char[]> temp = new ArrayList<>();

        try (Scanner scan = new Scanner(new FileInputStream("src/day6/testinput"))) {
            while (scan.hasNextLine()) {
                char[] row = scan.nextLine().toCharArray();
                temp.add(row);
                mapWidth = row.length;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        map = new char[temp.size()][mapWidth];
        for (int i = 0; i < temp.size(); i++) {
            for (int j = 0; j < mapWidth; j++) {
                map[i][j] = temp.get(i)[j];
            }
        }

        int[] pos = getGuardPosition();

        System.out.println(predictGuardMovement(map, pos));
        System.out.println(numObstructions);


    }

    public int[] getGuardPosition() {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == '^') {
                    return new int[]{row, col};
                }
            }
        }
        return null;
    }

    public int predictGuardMovement(char[][] map, int[] guardPos) {
        int guardRow = guardPos[0];
        int guardCol = guardPos[1];
        List<int[]> positions = new ArrayList<>();

        positions.add(new int[]{guardRow, guardCol});

        while (true) {
            while (true) {
                if (guardRow - 1 < 0) {
                    return positions.size();
                }
                if (map[guardRow - 1][guardCol] != '#') {
                    guardRow--;
                    int[] pos = {guardRow, guardCol};
                    if (!isPositionVisited(positions, pos)) {
                        positions.add(pos);
                    }
                } else {
                    break;
                }
            }
            while (true) {
                if (guardCol + 1 >= mapWidth) {
                    return positions.size();
                }
                if (map[guardRow][guardCol + 1] != '#') {
                    guardCol++;
                    int[] pos = {guardRow, guardCol};
                    if (!isPositionVisited(positions, pos)) {
                        positions.add(pos);
                    }
                } else {
                    break;
                }
            }
            while (true) {
                if (guardRow + 1 >= map.length) {
                    return positions.size();
                }
                if (map[guardRow + 1][guardCol] != '#') {
                    guardRow++;
                    int[] pos = {guardRow, guardCol};
                    if (!isPositionVisited(positions, pos)) {
                        positions.add(pos);
                    }
                } else {
                    break;
                }
            }
            while (true) {
                if (guardCol - 1 < 0) {
                    return positions.size();
                }
                if (map[guardRow][guardCol - 1] != '#') {
                    guardCol--;
                    int[] pos = {guardRow, guardCol};
                    if (!isPositionVisited(positions, pos)) {
                        positions.add(pos);
                    }
                } else {

                    break;
                }
            }
        }
    }

    public boolean isPositionVisited(List<int[]> positions, int[] currentPosition) {
        for (int[] position : positions) {
            if (position[0] == currentPosition[0] && position[1] == currentPosition[1]) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        new Day6();
    }
}
