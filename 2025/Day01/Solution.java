import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Path filePath = Paths.get("2025/Day01/input.txt");

        try {
            List<String> lines = Files.readAllLines(filePath);
            System.out.println("Advent Of Code 2025, --- Day 1: Secret Entrance --- Solutions: ");
            solutionBothDays(lines);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void solutionBothDays(List<String> lines) {
        int dialPointer = 50;
        int dialPointsAtZero = 0;
        int dialPassesZero = 0;

        for (String line : lines) {
            int turn = extractIntFromLine(line);

            while (turn > 0) {
                if (line.startsWith("L")) {
                    dialPointer --;
                    if (dialPointer == 0) dialPassesZero ++;
                    if (dialPointer == -1) dialPointer = 99;

                } else {
                    dialPointer ++;
                    if (dialPointer == 100) dialPointer = 0;
                    if (dialPointer == 0) dialPassesZero ++;
                }
                turn --;
            }

            if (dialPointer == 0) dialPointsAtZero ++;
        }
        System.out.println(" -> Part One: " + dialPointsAtZero);
        System.out.println(" -> Part Two: " + dialPassesZero);
    }

    public static int extractIntFromLine(String line) {
        String numberOnly = line.replaceAll("\\D+", ""); //
        return Integer.parseInt(numberOnly);
    }
}
