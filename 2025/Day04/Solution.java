package Day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("2025/Day04/input.txt");

        try {
            System.out.println("Advent Of Code 2025 --- Day 4: Printing Department --- Solutions: ");

            List<String> lines = Files.readAllLines(filePath);

            int rows = lines.size();
            int cols = lines.get(0).length();
            int[][] grid = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                String line = lines.get(i);
                for (int j = 0; j < cols; j++) {
                    char c = line.charAt(j);
                    grid[i][j] = (c == '@') ? 1: 0;
                }
            }

            solutionBothDays(grid, rows, cols);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solutionBothDays(int[][] grid, int rows, int cols) {

        int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};

        int totalRemoveRolls = 0;
        boolean nothingToRemove = true;
        boolean partOne = true;

        while (nothingToRemove) {

            int removedRolls = 0;
            int[][] gridCopy = copyGrid(grid);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int current = grid[i][j];

                    if (current == 1) {
                        int blocade = 0;
                        for (int k = 0; k < 8; k++) {
                            int ni = i + dRow[k];
                            int nj = j + dCol[k];

                            if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                                int neighbor = grid[ni][nj];
                                if (neighbor == 1) blocade ++;
                            }
                        }
                        if (blocade < 4) {
                            totalRemoveRolls++;
                            removedRolls ++;
                            gridCopy[i][j] = 0;
                        }

                    }
                }
            }

            grid = copyGrid(gridCopy);

            if (partOne) {
                partOne = false;
                System.out.println(" -> Part One: " + totalRemoveRolls);
            }

            if (removedRolls == 0) {
                nothingToRemove = false;
                System.out.println(" -> Part Two: " + totalRemoveRolls);
            }
        }
    }

    public static int[][] copyGrid(int[][] original) {
        int[][] copy = Arrays.stream(original)
                .map(row -> Arrays.copyOf(row, row.length))
                .toArray(int[][]::new);
        return copy;
    }

}
