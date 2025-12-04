package Day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("2025/Day03/input.txt");

        try {
            System.out.println("Advent Of Code 2025 --- Day 3: Lobby --- Solutions: ");

            List<String> lines = Files.readAllLines(filePath);

            solutionBothDays(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solutionBothDays(List<String> lines) {
        int totalVoltagePartOne = 0;
        long totalVoltagePartTwo = 0;

        for (String line : lines) {

//            System.out.println("line:  " + line);

            totalVoltagePartOne += Integer.valueOf(biggestNumberKDigits(line, 2));
            totalVoltagePartTwo += Long.valueOf(biggestNumberKDigits(line, 12));
        }
        System.out.println(" -> Part One: " + totalVoltagePartOne);
        System.out.println(" -> Part Two: " + totalVoltagePartTwo);

    }

    public static String biggestNumberKDigits(String line, int k) {
        int n = line.length();
        char[] stack = new char[k];
        int top = 0;

        for (int i = 0; i < n; i++) {
            char c = line.charAt(i);

            while (top > 0 && stack[top - 1] < c && n - i + top - 1 >= k) {
                top--;
            }

            if (top < k) {
                stack[top++] = c;
            }
        }

        return new String(stack);
    }
}
