package Day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("2025/Day04/input.txt");

        try {
            System.out.println("Advent Of Code 2025 --- Day 6: Trash Compactor --- Solutions: ");

            List<String> lines = Files.readAllLines(filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
