package Day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("2025/Day05/input.txt");

        try {
            System.out.println("Advent Of Code 2025 --- Day 5: Cafeteria --- Solutions: ");

            List<String> lines = Files.readAllLines(filePath);
            List<long[]> ranges = new ArrayList<>();
            boolean isRangePart = true;
            int correct = 0;

            for (String line : lines) {
                if (line.isBlank()) {
                    isRangePart = false;
                    continue;
                }

                if (isRangePart) {
                    String[] parts = line.split("-");
                    long start = Long.parseLong(parts[0].trim());
                    long end = Long.parseLong(parts[1].trim());
                    ranges.add(new long[]{start, end});

                } else {
                    //part one
                    for (long[] r : ranges) {
                        if (Long.parseLong(line) >= r[0] && Long.parseLong(line) <= r[1]) {
                            correct++;
                            break;
                        }
                    }

                }
            }

            System.out.println(" -> Part One: " + correct);
            partTwoSolution(ranges);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void partTwoSolution(List<long[]> ranges) {
        long allNumbersInRange = 0;
        ranges.sort(Comparator.comparingLong(a -> a[0]));

        List<long[]> merged = new ArrayList<>();
        for (long[] range : ranges) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < range[0] - 1) {
                merged.add(range);
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], range[1]);
            }
        }

        for (long[] r : merged) {
            allNumbersInRange += r[1] - r[0] + 1; // inclusive
        }

        System.out.println(" -> Part Two: " + allNumbersInRange);
    }
}
