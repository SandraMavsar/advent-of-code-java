package Day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("2025/Day02/input.txt");

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                line = line.replaceAll("[\\[\\]]", "");
                String[] parts = line.split("\\s*,\\s*");

                System.out.println("Advent Of Code 2025 --- Day 2: Gift Shop --- Solutions: ");
                solutionBothDays(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void solutionBothDays(String[] parts) {
        long invalidIdsPartOne = 0;
        long invalidIdsPartTwo = 0;

        for (String part : parts) {

            String[] bounds = part.split("-");
            if (bounds.length == 2) {
                long start = Long.parseLong(bounds[0].trim());
                long end = Long.parseLong(bounds[1].trim());

                for (long i = start; i <= end; i++) {

                    String idToCheck = String.valueOf(i);

                    //partOne
                    if (idToCheck.length() % 2 == 0) {
                        int half = idToCheck.length() / 2;
                        List<String> bothParts = splitIntoParts(idToCheck, half);

                        if (bothParts.size() == 1) {
                            invalidIdsPartOne += i;
                            invalidIdsPartTwo += i;
                            continue;
                        }
                    }

                    //partTwo
                    for (int charSize = 1; charSize < idToCheck.length(); charSize++) {
                        List<String> bothParts = splitIntoParts(idToCheck, charSize);
                        if (bothParts.size() == 1) {
                            invalidIdsPartTwo += i;
                            break;
                        }
                    }
                }
            } else {
                System.out.println("  (not a range)");
            }
        }

        System.out.println(" -> Part One: " + invalidIdsPartOne);
        System.out.println(" -> Part Two: " + invalidIdsPartTwo);

    }

    public static List<String> splitIntoParts(String s, int size) {
        List<String> parts = new ArrayList<>();

        for (int i = 0; i < s.length(); i += size) {
            String chunk = s.substring(i, Math.min(i + size, s.length()));

            if (!parts.contains(chunk)) {
                parts.add(chunk);
            }
        }

        return parts;
    }
}
